/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package yuber.api.service;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import yuber.interfaces.IUsuario;
import yuber.shares.DataTenant;
import yuber.shares.DataUsuario;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
@Remote
public class UsuarioRepo {
	@EJB(lookup = "java:app/yuberb/UsuarioCtrl!yuber.interfaces.IUsuario")
	IUsuario ctrUsuario;

	public DataUsuario AltaUsuario(DataUsuario usuario, DataTenant tenant) {
		return ctrUsuario.AltaUsuario(usuario, tenant);
	}

	public String get() {
		// TODO Auto-generated method stub
		return ctrUsuario.get();
	}

	public DataUsuario loginUsuario(String usuario, String clave, DataTenant tenant) {
		return ctrUsuario.loginUsuario(usuario, clave, tenant);
	}

	 
}
