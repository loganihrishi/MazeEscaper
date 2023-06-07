package main.persistence;

public interface Writable {
    // EFFECTS: converts the object to the CSV object and returns as a String
    public String toCSV();

}
