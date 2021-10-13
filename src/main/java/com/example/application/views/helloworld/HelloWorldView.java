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

@PageTitle("Hello World")
@Route(value = "hello", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class HelloWorldView extends HorizontalLayout {

    private PersonService personService;

    private TextField name;
    private Button sayHello;

    public HelloWorldView(@Autowired PersonService personService) {
        addClassName("hello-world-view");
        name = new TextField("Your name");
        sayHello = new Button("Say hello");
        add(name, sayHello);
        setVerticalComponentAlignment(Alignment.END, name, sayHello);
        sayHello.addClickListener(e -> {
            Notification.show("Hello " + name.getValue());
        });

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
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (InvalidFormatException invalidFormatException) {
                invalidFormatException.printStackTrace();
            }
        });
        add(upload);
    }

}
