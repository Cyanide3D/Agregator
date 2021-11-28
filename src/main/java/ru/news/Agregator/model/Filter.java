package ru.news.Agregator.model;

import java.time.LocalDateTime;

public class Filter {

    private String daysAgo;
    private String partOfName;

    public String getDaysAgo() {
        return daysAgo;
    }

    public void setDaysAgo(String daysAgo) {
        this.daysAgo = daysAgo;
    }

    public String getPartOfName() {
        return partOfName;
    }

    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;
    }

    public LocalDateTime getSearchDate() {
        return LocalDateTime.now().minusDays(Integer.parseInt(daysAgo));
    }

    @Override
    public String toString() {
        return "Filter{" +
                "daysAgo='" + daysAgo + '\'' +
                ", partOfName='" + partOfName + '\'' +
                '}';
    }
}
