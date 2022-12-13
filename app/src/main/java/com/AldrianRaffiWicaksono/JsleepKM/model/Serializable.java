package com.AldrianRaffiWicaksono.JsleepKM.model;


/**
 * A base class for objects that can be serialized and assigned an ID.
 *
 * <p>This class provides an ID field and a simple mechanism for assigning unique IDs to objects of subclasses.
 * It also provides methods for comparing objects by ID and for managing the ID counter for each subclass.</p>
 *
 * @author Aldrian Raffi Wicaksono
 */
public class Serializable {
    public final int id;

    public Serializable(int id) {
        this.id = id;
    }

}
