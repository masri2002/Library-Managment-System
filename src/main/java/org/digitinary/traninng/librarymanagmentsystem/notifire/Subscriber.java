package org.digitinary.traninng.librarymanagmentsystem.notifire;

import org.digitinary.traninng.librarymanagmentsystem.entity.Loan;

public interface Subscriber {
    void update(Loan loan);
}
