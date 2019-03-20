/*******************************************************************************
 * This file is part of OpenNMS(R).
 *
 * Copyright (C) 2019 The OpenNMS Group, Inc.
 * OpenNMS(R) is Copyright (C) 1999-2019 The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is a registered trademark of The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * OpenNMS(R) is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with OpenNMS(R).  If not, see:
 *      http://www.gnu.org/licenses/
 *
 * For more information contact:
 *     OpenNMS(R) Licensing <license@opennms.org>
 *     http://www.opennms.org/
 *     http://www.opennms.com/
 *******************************************************************************/

package org.opennms.plugins.config.DellDataDomain;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.opennms.integration.api.v1.config.events.EventDefinition;
import org.opennms.integration.api.v1.model.Severity;

import java.util.List;

public class ConfigTest {

    @Test
    public void canLoadSnmpCollectionExtension() {
        DellDataDomainSnmpCollectionExtensionImpl snmpCollectionExtension = new DellDataDomainSnmpCollectionExtensionImpl();
        assertThat(snmpCollectionExtension.getSnmpDataCollectionGroups(), hasSize(1));
    }

    @Test
    public void canLoadGraphPropertiesExtension() {
        DellDataDomainGraphPropertiesExtension graphPropertiesExtension = new DellDataDomainGraphPropertiesExtension();
        assertThat(graphPropertiesExtension.getPrefabGraphs(), hasSize(47));
    }

    @Test
    public void canLoadResourceTypesExtension() {
        DellDataDomainResourceTypesExtension resourceTypesExtension = new DellDataDomainResourceTypesExtension();
        assertThat(resourceTypesExtension.getResourceTypes(), hasSize(8));
    }

    @Test
    public void canReadEventDefinitionsFromExtension() {
        DellDataDomainEventsExtension DellDataDomainEventConfExtension = new DellDataDomainEventsExtension();
        List<EventDefinition> eventDefinitions = DellDataDomainEventConfExtension.getEventDefinitions();
        assertThat(eventDefinitions, hasSize(280));
    }

    @Test
    public void testAliasRrdMax19CharLimit() {
        DellDataDomainSnmpCollectionExtensionImpl snmpCollectionExtension = new DellDataDomainSnmpCollectionExtensionImpl();
        snmpCollectionExtension.getSnmpDataCollectionGroups().forEach(collectionGroup -> collectionGroup.getGroups().forEach(group -> group.getMibObjs().forEach(mibObj -> assertThat(mibObj.getAlias().length(), lessThanOrEqualTo(19)))));
    }
}
