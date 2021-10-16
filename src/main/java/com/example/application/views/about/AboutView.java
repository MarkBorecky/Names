package com.example.application.views.about;

import com.example.application.data.entity.Person;
import com.example.application.data.service.PersonService;
import com.example.application.services.ExcelFileWriter;
import com.example.application.services.ODSWriter;
import com.example.application.utils.StringUtils;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@PageTitle("About")
@Route(value = "about", layout = MainLayout.class)
public class
AboutView extends Div implements BeforeEnterObserver {

	private Grid<Person> grid = new Grid<>();
	private List<Person> people = new ArrayList<>();
	private PersonService personService;
	private ExcelFileWriter writer;

	private int results = 0;

	TextField name = new TextField("Imię");
	long nameAmount = 0;
	TextField surname = new TextField("Nazwisko");
	int surnameAmount = 0;
	TextField patronus = new TextField("Imię ojca");
	int patronusAmount = 0;
	TextField goverment = new TextField("Gubernia");
	int govermentAmount = 0;
	TextField uyezd = new TextField("Ujazd");
	int uyezdAmount = 0;
	TextField selo = new TextField("Sioło");
	int seloAmount = 0;
	TextField fatherOccupation = new TextField("Zawód ojca");
	int fatherOccupationAmount = 0;
	TextField number = new TextField("Numer");
	int numberAmount = 0;
	TextField school = new TextField("Szkoła");
	int schoolAmount = 0;
	TextField rok = new TextField("Rok");
	int rokAmount = 0;

	String resultString = "Znaleziono wyników %d";
	H3 resultText = new H3(String.format(resultString, results));

	VerticalLayout form = new VerticalLayout();
	boolean formVisible = true;
	Button button = new Button();

	public AboutView(@Autowired PersonService personService) {
		writer = new ODSWriter();
		this.personService = personService;
		setButtonSearchForm();
		addClassNames("about-view", "flex", "flex-col", "h-full");
		List<Person> all = personService.getAll();
		setResultText(all.size());
		configureSearchForm();
		configureGrid(all);
	}

	private long getNumberDistinctValues(List<Person> all, ValueProvider<Person, String> getter) {
		return all.stream()
				.map(getter)
				.filter(StringUtils::isNotEmpty)
				.distinct()
				.count();
	}

	private long getNumberDistinctValues2(List<Person> all, ValueProvider<Person, Integer> getter) {
		return all.stream()
				.map(getter)
				.filter(StringUtils::isNotEmpty)
				.distinct()
				.count();
	}

	private void setButtonSearchForm() {
		button = new Button("formularz", e -> {
			form.setVisible((formVisible = !formVisible));
			Notification.show("form visible = " + formVisible);
		});
		add(button);
	}

	private void configureSearchForm() {
		addSearchInput(name, surname, patronus, goverment, uyezd, selo, fatherOccupation, number, school, rok);
	}

	private void configureGrid(List<Person> all) {
		people = all;
		grid.setItems(all);
		addColumn(all, "Imię", Person::getNameToString);
		addColumn(all, "Nazwisko", Person::getSurname);
		addColumn(all, "Imię ojca", Person::getPatronus);
		addColumn(all, "Gubernia", Person::getGoverment);
		addColumn(all, "Ujazd", Person::getUyezd);
		addColumn(all, "Sioło", Person::getSelo);
		addColumn(all, "Zawód", Person::getFatherOccupation);
		addColumnInt(all, "Numer", Person::getNumber);
		addColumn(all, "Szkoła", Person::getSchool);
		addColumnInt(all, "Rok", Person::getYear);

		grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
		grid.setHeightFull();
		add(grid);
	}

	private void addColumn(List<Person> all, String columnName, ValueProvider<Person, String> getter) {
		addColumn(getter, String.format("%s (%d)\n", columnName, getNumberDistinctValues(all, getter)));
	}

	private void addColumn(ValueProvider<Person, String> provider, String header) {
		grid.addColumn(provider)
				.setHeader(header)
				.setComparator(Comparator.comparing(provider))
				.setResizable(true)
				.setWidth("250px");
	}

	private void addColumnInt(List<Person> all, String columnName, ValueProvider<Person, Integer> getter) {
		addColumnInt(getter, String.format("%s (%d)\n", columnName, getNumberDistinctValues2(all, getter)));
	}

	private void addColumnInt(ValueProvider<Person, Integer> provider, String header) {
		grid.addColumn(provider)
				.setHeader(header)
				.setComparator(Comparator.comparing(provider))
				.setResizable(true);
	}

	public void updateList() {
		List<Person> all = personService.getAll(
				name.getValue(),
				surname.getValue(),
				patronus.getValue(),
				goverment.getValue(),
				uyezd.getValue(),
				selo.getValue(),
				fatherOccupation.getValue(),
				number.getValue(),
				school.getValue(),
				rok.getValue()
		);
		setResultText(all.size());
		people = all;
		grid.setItems(all);
	}

	private void setResultText(int size) {
		resultText.setText(String.format(resultString, size));
	}

	private void addSearchInput(TextField... fields) {
		var layout = new HorizontalLayout();
		layout.addClassName("contact-form");
		form.add(resultText);
		var i = 0;
		for (TextField field : fields) {
			field.setClearButtonVisible(true);
			field.setValueChangeMode(ValueChangeMode.LAZY);
			field.addValueChangeListener(e -> updateList());
			field.setWidth(25, Unit.PERCENTAGE);
			layout.add(field);
			i++;
			if (i == 5) {
				form.add(layout);
				layout = new HorizontalLayout();
			}
		}
		form.add(layout);
		createDownloadButton();
		add(form);
	}

	private void createDownloadButton() {
		var name = "people.ods";
		InputStream input = download(name);
		Anchor download = new Anchor(new StreamResource(name, () -> download(name)), "");
		download.getElement().setAttribute("download", true);
		download.add(new Button("Pobierz", new Icon(VaadinIcon.DOWNLOAD_ALT)));
		add(download);
	}

	private InputStream download(String name) {
		try {
			return writer.write(people, name);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void beforeEnter(BeforeEnterEvent event) {

	}

}
