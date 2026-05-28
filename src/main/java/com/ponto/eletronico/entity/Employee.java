    package com.ponto.eletronico.entity;

    import com.ponto.eletronico.converter.DurationConverter;
    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    import java.math.BigDecimal;
    import java.time.Duration;
    import java.util.List;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @Table(name = "EMPLOYEE")
    @Entity
    public class Employee {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        private String email;
        private BigDecimal salary;
        @Convert(converter = DurationConverter.class)
        private Duration hoursWorked = Duration.ZERO;




    }
