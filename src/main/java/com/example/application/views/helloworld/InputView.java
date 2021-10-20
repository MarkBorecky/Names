package com.example.application.views.helloworld;

import com.example.application.data.entity.Person;
import com.example.application.data.service.PersonService;
import com.example.application.services.FileReader;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.example.application.views.MainLayout;
import com.vaadin.flow.router.RouteAlias;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

@PageTitle("Wgraj plik")
@Route(value = "input", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class InputView extends HorizontalLayout {

    private PersonService personService;

    public InputView(@Autowired PersonService personService) {
        MemoryBuffer memoryBuffer = new MemoryBuffer();
        Upload upload = new Upload(memoryBuffer);
        upload.addFinishedListener(e -> {
            var inputStream = memoryBuffer.getInputStream();
            var fileName = memoryBuffer.getFileName();
            var reader = FileReader.getReader(fileName);
            try {
                List<Person> read = reader.read(inputStream);
                Notification.show(String.format("%s linii", read.size()));
                personService.deleteAll();
                personService.saveAll(read);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        add(upload);
    }

}
