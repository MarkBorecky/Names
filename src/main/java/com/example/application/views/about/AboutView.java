package com.example.application.views.about;

import com.example.application.data.entity.DataDao;
import com.example.application.data.service.DataDaoService;
import com.example.application.views.MainLayout;
import com.vaadin.collaborationengine.CollaborationBinder;
import com.vaadin.collaborationengine.UserInfo;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.artur.helpers.CrudServiceDataProvider;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

@PageTitle("About")
@Route(value = "about", layout = MainLayout.class)
public class AboutView extends Div implements BeforeEnterObserver {

    private Grid<DataDao> grid = new Grid<>();
    private DataDaoService dataDaoService;

    public AboutView(@Autowired DataDaoService dataDaoService) {
        this.dataDaoService = dataDaoService;
        addClassNames("about-view", "flex", "flex-col", "h-full");
        List<DataDao> all = dataDaoService.getAll();
        configureGrid(all);
    }

    private void configureGrid(List<DataDao> all) {
        grid.setItems(all);
        addColumn(DataDao::getName, "Imię");
        addColumn(DataDao::getSurname, "Nazwisko");
        addColumn(DataDao::getPatronus, "Imię");
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
                .setResizable(true);
    }

    private void addColumnInt(ValueProvider<DataDao, Integer> provider, String header) {
        grid.addColumn(provider)
                .setHeader(header)
                .setComparator(Comparator.comparing(provider))
                .setResizable(true);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {

    }

}
