package com.github.appreciated.app.layout.component;

import com.vaadin.server.Resource;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import java.util.*;

import static com.github.appreciated.app.layout.Styles.*;

public class ExpandingMenuContainer extends VerticalLayout {

    private static Map<UI, List<ExpandingMenuContainer>> containers = new HashMap<>();

    private final VerticalLayout submenuWrapper;
    private NavigationButton expandMenuButton;

    public ExpandingMenuContainer(String sectionName, Resource icon) {
        addStyleName(EXPANDING_MENU_CONTAINER_PRIMARY_STYLE);
        setMargin(false);

        expandMenuButton = new NavigationButton(sectionName, icon);
        expandMenuButton.addClickListener(clickEvent -> {
            for (ExpandingMenuContainer container : containers.get(UI.getCurrent())) {
                List<String> idsInHierarchy = Arrays.asList(getId().split(">"));
                if (!idsInHierarchy.contains(container.getId())) {
                    container.close();
                }
            }
            if (getStyleName().contains(EXPANDING_MENU_CONTAINER_OPEN)) {
                removeStyleName(EXPANDING_MENU_CONTAINER_OPEN);
            } else {
                addStyleName(EXPANDING_MENU_CONTAINER_OPEN);
            }
        });
        HorizontalLayout buttonWrapper = new HorizontalLayout(expandMenuButton);
        buttonWrapper.addStyleName(EXPANDING_MENU_CONTAINER_BUTTON);
        buttonWrapper.setSpacing(false);
        buttonWrapper.setWidth(100, Unit.PERCENTAGE);
        super.addComponent(buttonWrapper);

        submenuWrapper = new VerticalLayout();
        submenuWrapper.addStyleName(EXPANDING_MENU_SUBMENU_CONTAINER);
        submenuWrapper.setMargin(false);
        submenuWrapper.setWidth(100, Unit.PERCENTAGE);

        super.addComponent(buttonWrapper);
        super.addComponent(submenuWrapper);

        if (!containers.containsKey(UI.getCurrent())) {
            containers.put(UI.getCurrent(), new ArrayList<>());
            UI.getCurrent().addDetachListener((DetachListener) event -> containers.remove(UI.getCurrent()));
        }
        containers.get(UI.getCurrent()).add(this);
    }

    public NavigationButton getExpandMenuButton() {
        return expandMenuButton;
    }

    @Override
    public void addComponent(Component c) {
        submenuWrapper.addComponent(c);
    }

    public void close() {
        if (getStyleName().contains(EXPANDING_MENU_CONTAINER_OPEN)) {
            removeStyleName(EXPANDING_MENU_CONTAINER_OPEN);
        }
    }
}
