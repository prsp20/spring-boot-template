package com.prakass.example.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "example")
public class Example {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String content;

    public Example() {
    }

    public Example(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Example example = (Example) o;
        return Objects.equals(id, example.id) &&
                Objects.equals(name, example.name) &&
                Objects.equals(content, example.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, content);
    }

    public static class Builder {
        private String name;
        private String content;

        public static Builder anExample() {
            return new Builder();
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Example build() {
            return new Example(this.name, this.content);
        }
    }
}
