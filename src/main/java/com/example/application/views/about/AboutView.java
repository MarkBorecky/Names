package com.example.application.views.about;

import com.example.application.data.entity.DataDao;
import com.example.application.data.service.DataDaoService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
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
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Comparator;
import java.util.List;

@PageTitle("About")
@Route(value = "about", layout = MainLayout.class)
public class AboutView extends Div implements BeforeEnterObserver {

	private Grid<DataDao> grid = new Grid<>();
	private DataDaoService dataDaoService;

	private int results = 0;

	TextField name = new TextField("Imię");
	TextField surname = new TextField("Nazwisko");
	TextField patronus = new TextField("Imię ojca");
	TextField goverment = new TextField("Gubernia");
	TextField uyezd = new TextField("Ujazd");
	TextField selo = new TextField("Sioło");
	TextField fatherOccupation = new TextField("Zawód ojca");
	TextField number = new TextField("Numer");
	TextField school = new TextField("Szkoła");
	TextField rok = new TextField("Rok");

	String resultString = "Znaleziono wyników %d";
	H3 resultText = new H3(String.format(resultString, results));

	VerticalLayout form = new VerticalLayout();
	boolean formVisible = true;
	Button button = new Button();

	public AboutView(@Autowired DataDaoService dataDaoService) {
		this.dataDaoService = dataDaoService;
		setButtonSearchForm();
		addClassNames("about-view", "flex", "flex-col", "h-full");
		List<DataDao> all = dataDaoService.getAll();
		setResultText(all.size());
		configureSearchForm();
		configureGrid(all);
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

	private void configureGrid(List<DataDao> all) {
		grid.setItems(all);
		addColumn(DataDao::getName, "Imię");
		addColumn(DataDao::getSurname, "Nazwisko");
		addColumn(DataDao::getPatronus, "Imię ojca");
		addColumn(DataDao::getGoverment, "Gubernia");
		addColumn(DataDao::getUyezd, "Ujazd");
		addColumn(DataDao::getSelo, "Sioło");
		addColumn(DataDao::getFatherOccupation, "Zawód ojca");
		addColumnInt(DataDao::getNumber, "Numer");
		addColumn(DataDao::getSchool, "Szkoła");
		addColumnInt(DataDao::getYear, "Rok");
		grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
		grid.setHeightFull();
		add(grid);
	}

	private void addColumn(ValueProvider<DataDao, String> provider, String header) {
		grid.addColumn(provider)
				.setHeader(header)
				.setComparator(Comparator.comparing(provider))
				.setResizable(true)
				.setWidth("250px");
	}

	private void addColumnInt(ValueProvider<DataDao, Integer> provider, String header) {
		grid.addColumn(provider)
				.setHeader(header)
				.setComparator(Comparator.comparing(provider))
				.setResizable(true);
	}

	public void updateList() {
		List<DataDao> all = dataDaoService.getAll(
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
		form.add(new Button("pobierz wynik", e -> Notification.show("Pobieram wynik...")));
		add(form);
	}

	@Override
	public void beforeEnter(BeforeEnterEvent event) {

	}

}
