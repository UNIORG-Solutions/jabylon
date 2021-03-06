/**
 * (C) Copyright 2013 Jabylon (http://www.jabylon.org) and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.jabylon.users;

import org.eclipse.emf.cdo.CDOObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Permission</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jabylon.users.Permission#getName <em>Name</em>}</li>
 *   <li>{@link org.jabylon.users.Permission#getDescription <em>Description</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jabylon.users.UsersPackage#getPermission()
 * @model
 * @extends CDOObject
 * @generated
 */
public interface Permission extends CDOObject {
    /**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.jabylon.users.UsersPackage#getPermission_Name()
	 * @model annotation="http://www.eclipse.org/CDO/DBStore columnType='VARCHAR' columnLength='255'"
	 * @generated
	 */
    String getName();

    /**
	 * Sets the value of the '{@link org.jabylon.users.Permission#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
    void setName(String value);

    /**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Description</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see org.jabylon.users.UsersPackage#getPermission_Description()
	 * @model annotation="http://www.eclipse.org/CDO/DBStore columnType='VARCHAR' columnLength='1024'"
	 * @generated
	 */
    String getDescription();

    /**
	 * Sets the value of the '{@link org.jabylon.users.Permission#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
    void setDescription(String value);

} // Permission
