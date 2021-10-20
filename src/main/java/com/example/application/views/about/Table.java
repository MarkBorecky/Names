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
import com.vaadin.flow.component.textfield.IntegerField;
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
public class Table extends Div {

    final private Grid<Person> grid = new Grid<>();
    private List<Person> people = new ArrayList<>();
    final private PersonService personService;
    final private ExcelFileWriter writer;
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
    IntegerField yearFrom = new IntegerField(YEAR);
    IntegerField yearUntil = new IntegerField(YEAR);

    String resultString = "Znaleziono wyników %d";
    H3 resultText = new H3(String.format(resultString, 0));

    VerticalLayout form = new VerticalLayout();
    boolean formVisible = false;

    public Table(@Autowired PersonService personService) {
        this.personService = personService;
        this.writer = new ODSWriter();
        setButtonSearchForm();
        addClassNames("about-view", "flex", "flex-col", "h-full");
        List<Person> all = personService.getAll();
        setResultText(all.size());
        configureSearchForm();
        configureGrid(all);
    }

    private void setButtonSearchForm() {
        add(new Button("formularz", e -> form.setVisible((formVisible = !formVisible))));
    }

    private void configureSearchForm() {
        form.add(resultText);
        var layout1 = new HorizontalLayout();
        layout1.addClassName("contact-form");
        addFieldToLayout(layout1, mainName);
        addFieldToLayout(layout1, originalName);
        addFieldToLayout(layout1, surname);
        addFieldToLayout(layout1, patronus);
        addFieldToLayout(layout1, goverment);
        addFieldToLayout(layout1, uyezd);
        form.add(layout1);

        var layout2 = new HorizontalLayout();
        layout1.addClassName("contact-form");
        addFieldToLayout(layout2, selo);
        addFieldToLayout(layout2, fatherOccupation);
        addFieldToLayout(layout2, number);
        addFieldToLayout(layout2, school);
        addFieldToLayout(layout2, yearFrom);
        addFieldToLayout(layout2, yearUntil);
        form.add(layout2);

        createDownloadButton();
        add(form);
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
                yearFrom.getValue(),
                yearUntil.getValue()
        );
        setResultText(all.size());
        people = all;
        updateGrid(all);
    }

    private void updateGrid(List<Person> all) {

        grid.setItems(all);
        resultMap.put(MAIN_NAME, getNumberDistinctValues(all, Person::getMainName));
        resultMap.put(ORIGINAL_NAME, getNumberDistinctValues(all, Person::getOriginalName));
        resultMap.put(SURNAME, getNumberDistinctValues(all, Person::getSurname));
        resultMap.put(PATRONUS, getNumberDistinctValues(all, Person::getPatronus));
        resultMap.put(GOVERMENT, getNumberDistinctValues(all, Person::getGoverment));
        resultMap.put(UYEZD, getNumberDistinctValues(all, Person::getUyezd));
        resultMap.put(SELO, getNumberDistinctValues(all, Person::getSelo));
        resultMap.put(FATHER_OCCUPATION, getNumberDistinctValues(all, Person::getFatherOccupation));
        resultMap.put(NUMBER, getNumberDistinctValues2(all, Person::getNumber));
        resultMap.put(SCHOOL, getNumberDistinctValues(all, Person::getSchool));
        resultMap.put(YEAR, getNumberDistinctValues2(all, Person::getYear));
        grid.getColumns().forEach(x -> x.setHeader(String.format("%s (%d)", x.getKey(), resultMap.get(x.getKey()))));
    }

    private void setResultText(int size) {
        resultText.setText(String.format(resultString, size));
    }

    private void addFieldToLayout(HorizontalLayout layout, TextField field) {
        field.setClearButtonVisible(true);
        field.setValueChangeMode(ValueChangeMode.LAZY);
        field.addValueChangeListener(e -> updateList());
        field.setWidth(25, Unit.PERCENTAGE);
        layout.add(field);
    }

    private void addFieldToLayout(HorizontalLayout layout, IntegerField field) {
        field.setClearButtonVisible(true);
        field.setValueChangeMode(ValueChangeMode.LAZY);
        field.addValueChangeListener(e -> updateList());
        field.setWidth(25, Unit.PERCENTAGE);
        layout.add(field);
    }

    private void createDownloadButton() {
        var name = "people.ods";
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
}