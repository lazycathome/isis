/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.apache.isis.viewer.wicket.ui.components.actionprompt;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.AbstractReadOnlyModel;

import org.apache.isis.core.metamodel.adapter.ObjectAdapter;
import org.apache.isis.core.metamodel.facets.all.named.NamedFacet;
import org.apache.isis.core.metamodel.spec.feature.ObjectAction;
import org.apache.isis.viewer.wicket.model.models.ActionModel;
import org.apache.isis.viewer.wicket.ui.ComponentType;
import org.apache.isis.viewer.wicket.ui.panels.PanelAbstract;

/**
 * A panel used as a title for the action prompts
 */
public class ActionPromptHeaderPanel extends PanelAbstract<ActionModel> {

    private static final String ID_ACTION_NAME = "actionName";

    public ActionPromptHeaderPanel(String id, final ActionModel model) {
        super(id, model);

        ObjectAdapter targetAdapter = model.getTargetAdapter();

        getComponentFactoryRegistry().addOrReplaceComponent(this, ComponentType.ENTITY_ICON_AND_TITLE, model.getParentEntityModel());


        final Label label = new Label(ID_ACTION_NAME, new AbstractReadOnlyModel<String>() {
            @Override
            public String getObject() {
                final ObjectAction action = model.getActionMemento().getAction(getSpecificationLoader());
                return action.getName();
            }
        });

        final ObjectAction action = model.getActionMemento().getAction(getSpecificationLoader());
        NamedFacet namedFacet = action.getFacet(NamedFacet.class);
        if (namedFacet != null) {
            label.setEscapeModelStrings(namedFacet.escaped());
        }

        add(label);
    }

}
