package com.github.appreciated.app.layout.builder.providers;

import com.github.appreciated.app.layout.builder.ComponentProvider;
import com.github.appreciated.app.layout.builder.elements.SubmenuNavigationElement;
import com.github.appreciated.app.layout.component.ExpandingMenuContainer;
import com.vaadin.ui.Component;

import java.util.UUID;

public class DefaultSubmenuNavigationElementProvider implements ComponentProvider<SubmenuNavigationElement> {
    @Override
    public Component get(SubmenuNavigationElement element) {
        ExpandingMenuContainer container = new ExpandingMenuContainer(element.getTitle(), element.getIcon());
        container.setId(UUID.randomUUID().toString());
        element.getSubmenuElements().forEach(child -> {
            if (child instanceof SubmenuNavigationElement) {
                child.getComponent().setId(container.getId() + ">" + UUID.randomUUID().toString());
            }
            container.addComponent(child.getComponent());
        });
        return container;
    }
}