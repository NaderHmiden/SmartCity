package AttractionModels;

import AttractionModels.Place;
import Exception.InvalidInputException;

import java.util.List;
import java.util.Objects;

/**
 * Représente une attraction touristique (musée, parc, monument, etc.)
 * Classe finale héritant de Place.
 */
public final class Attraction extends Place {

    private final AttractionCategory category;
    private final List<OpeningHours> openingHours;
    private final double price;

    
    public Attraction(
            String id,
            String name,
            String description,
            Address address,
            Contact contact,
            Coordinates coordinates,
            AttractionCategory category,
            List<OpeningHours> openingHours,
            double price
    ) {
        // Appel au constructeur parent
        super(id, name, description, address, contact, coordinates);

        // Initialisation + validation
        this.category = Objects.requireNonNull(category, "category");
        this.openingHours = openingHours == null ? List.of() : List.copyOf(openingHours);

        if (price < 0) {
            throw new InvalidInputException("price must be >= 0");
        }
        this.price = price;
    }

    // Getters
    public AttractionCategory getCategory() { return category; }
    public List<OpeningHours> getOpeningHours() { return openingHours; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return "Attraction{" + getId() + " - " + getName() + " (" + category + ")}";
    }
}
