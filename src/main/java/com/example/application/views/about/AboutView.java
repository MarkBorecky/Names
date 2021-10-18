package com.example.application.views.about;

import com.example.application.data.entity.Person;
import com.example.application.data.service.PersonService;
import com.example.application.services.ExcelFileWriter;
import com.example.application.services.ODSWriter;
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
import java.util.*;
import static com.example.application.utils.PersonUtils.getNumberDistinctValues;
import static com.example.application.utils.PersonUtils.getNumberDistinctValues2;
import static com.example.application.utils.Headers.*;

@PageTitle("About")
@Route(value = "about", layout = MainLayout.class)
public class
AboutView extends Div implements BeforeEnterObserver {

    final private Grid<Person> grid = new Grid<>();
    private List<Person> people = new ArrayList<>();
    final private PersonService personService;
    final private ExcelFileWriter writer;

    private int results = 0;

    Map<String, Long> resultMap = new HashMap();

    TextField mainName = new TextField(MAIN_NAME);
    TextField originalName = new TextField(ORIGINAL_NAME);
    TextField surname = new TextField(SURNAME);
    TextField patronus = new TextField(PATRONUS);
    TextField goverment = new TextField(GOVERMENT);
    TextField uyezd = new TextField(UYEZD);
    TextField selo = new TextField(SELO);
    TextField fatherOccupation = new TextField(FATHER_OCCUPATION);
    TextField number = new TextField(NUMBER);
    TextField school = new TextField(SCHOOL);
    TextField rok = new TextField(YEAR);

    String resultString = "Znaleziono wyników %d";
    H3 resultText = new H3(String.format(resultString, results));

    VerticalLayout form = new VerticalLayout();
    boolean formVisible = false;
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

    private void setButtonSearchForm() {
        button = new Button("formularz", e -> {
            form.setVisible((formVisible = !formVisible));
            Notification.show("form visible = " + formVisible);
        });
        add(button);
    }

    private void configureSearchForm() {
        addSearchInput(mainName, originalName, surname, patronus, goverment, uyezd, selo, fatherOccupation, number, school, rok);
    }

    private void configureGrid(List<Person> all) {
        people = all;
        grid.setItems(all);
        addColumn(all, MAIN_NAME, Person::getMainName);
        addColumn(all, ORIGINAL_NAME, Person::getOriginalName);
        addColumn(all, SURNAME, Person::getSurname);
        addColumn(all, PATRONUS, Person::getPatronus);
        addColumn(all, GOVERMENT, Person::getGoverment);
        addColumn(all, UYEZD, Person::getUyezd);
        addColumn(all, SELO, Person::getSelo);
        addColumn(all, FATHER_OCCUPATION, Person::getFatherOccupation);
        addColumnInt(all, NUMBER, Person::getNumber);
        addColumn(all, SCHOOL, Person::getSchool);
        addColumnInt(all, YEAR, Person::getYear);
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.setHeightFull();
        add(grid);
    }

    private void addColumn(List<Person> all, String columnName, ValueProvider<Person, String> getter) {
        addColumn(getter, columnName, String.format("%s (%d)\n", columnName, getNumberDistinctValues(all, getter)));
    }

    private void addColumn(ValueProvider<Person, String> provider, String columnName, String header) {
        grid.addColumn(provider)
                .setHeader(header)
                .setKey(columnName)
                .setComparator(Comparator.comparing(provider))
                .setResizable(true)
                .setWidth("250px");
    }

    private void addColumnInt(List<Person> all, String columnName, ValueProvider<Person, Integer> getter) {
        addColumnInt(getter, columnName, String.format("%s (%d)\n", columnName, getNumberDistinctValues2(all, getter)));
    }

    private void addColumnInt(ValueProvider<Person, Integer> provider, String columnName, String header) {
        grid.addColumn(provider)
                .setHeader(header)
                .setKey(columnName)
                .setComparator(Comparator.comparing(provider))
                .setResizable(true);
    }

    public void updateList() {
        List<Person> all = personService.getAll(
                mainName.getValue(),
                originalName.getValue(),
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
        updateGrid(all);
    }

    private void updateGrid(List<Person> all) {

        grid.setItems(all);
        resultMap.put("Imię", getNumberDistinctValues(all, Person::getMainName));
        resultMap.put("Nazwisko", getNumberDistinctValues(all, Person::getSurname));
        resultMap.put("Imię ojca", getNumberDistinctValues(all, Person::getPatronus));
        resultMap.put("Gubernia", getNumberDistinctValues(all, Person::getGoverment));
        resultMap.put("Ujazd", getNumberDistinctValues(all, Person::getUyezd));
        resultMap.put("Sioło", getNumberDistinctValues(all, Person::getSelo));
        resultMap.put("Zawód", getNumberDistinctValues(all, Person::getFatherOccupation));
        resultMap.put("Numer", getNumberDistinctValues2(all, Person::getNumber));
        resultMap.put("Szkoła", getNumberDistinctValues(all, Person::getSchool));
        resultMap.put("Rok", getNumberDistinctValues2(all, Person::getYear));
        grid.getColumns().forEach(x -> x.setHeader(String.format("%s (%d)", x.getKey(), resultMap.get(x.getKey()))));
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
