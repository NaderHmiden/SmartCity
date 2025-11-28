/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AttractionModels;

/**
 *
 * @author manso
 */

/**
 * Functional interface représentant un objet identifiable par un id.
 */
@FunctionalInterface
public interface Identifiable {
    /**
     * Retourne l'identifiant unique (string) de l'objet.
     *
     * @return id unique
     */
    String getId();
}

