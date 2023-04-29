package excel.sql.email.emailsql;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Student {

    @Id
    private int id;
    private String name;
    private String email;
    @Column(name = "amount_remaining")
    private int amountRemaining;

    public Student(int id, String name, String email, int amountRemaining) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.amountRemaining = amountRemaining;
    }

    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAmountRemaining() {
        return amountRemaining;
    }

    public void setAmountRemaining(int amountRemaining) {
        this.amountRemaining = amountRemaining;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", amountRemaining=" + amountRemaining +
                '}';
    }
}
