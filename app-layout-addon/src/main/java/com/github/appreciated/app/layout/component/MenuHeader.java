package com.github.appreciated.app.layout.component;

import com.vaadin.server.Resource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Button;
import com.vaadin.ui.Image;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import static com.github.appreciated.app.layout.Styles.*;

public class MenuHeader extends VerticalLayout {
    public MenuHeader(Resource resource) {
        this(null, (String) null, resource, null);
    }

    public MenuHeader(String title, Resource resource) {
        this(title, (String) null, resource, null);
    }

    public MenuHeader(String title, String subtitle, Resource resource) {
        this(title, subtitle, resource != null ? new Image(null, resource) : null, null);
    }

    public MenuHeader(String title, Resource resource, Button logout) {
        this(title, null, resource != null ? new Image(null, resource) : null, logout);
    }

    public MenuHeader(String title, String subtitle, Resource resource, Button logout) {
        this(title, subtitle, resource != null ? new Image(null, resource) : null, logout);
    }

    public MenuHeader(String title, String subtitle, Image image, Button logout) {
        Label name = new Label(title);
        name.addStyleName(APP_LAYOUT_MENU_HEADER_TITLE);
        Label description = new Label(subtitle);
        description.addStyleName(APP_LAYOUT_MENU_HEADER_TITLE);
        description.addStyleName(ValoTheme.LABEL_SMALL);
        addStyleName(APP_LAYOUT_MENU_BAR_ELEMENT);
        addStyleName(APP_LAYOUT_MENU_HEADER_ELEMENT);
        setMargin(false);
        setSpacing(false);
        setMargin(new MarginInfo(true, false, false, false));
        if (image != null)
            image.setHeight("75px");
        addComponent(image);
        if (title != null)
            addComponent(name);
        if (subtitle != null)
            addComponent(description);
        if (logout != null) {
            addComponent(logout);
        }
    }


    public MenuHeader(String title, Component companyChooser, Resource resource, Button logout) {
        Label name = new Label(title);
        name.addStyleName(APP_LAYOUT_MENU_HEADER_TITLE);
        addStyleName(APP_LAYOUT_MENU_BAR_ELEMENT);
        addStyleName(APP_LAYOUT_MENU_HEADER_ELEMENT);
        setMargin(false);
        setSpacing(false);
        setMargin(new MarginInfo(true, false, false, false));
        if (resource != null) {
            Image image = new Image(null, resource);
            image.setHeight("75px");
            image.addStyleName(APP_LAYOUT_MENU_HEADER_TITLE);
            addComponent(image);
        }
        if (title != null)
            addComponent(name);
        if (companyChooser != null) {
            addComponent(companyChooser);
            companyChooser.addStyleName(APP_LAYOUT_MENU_HEADER_TITLE);
        }
        if (logout != null) {
            addComponent(logout);
        }
    }

}