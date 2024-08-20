package org.digitinary.traninng.librarymanagmentsystem.notifire;

import org.digitinary.traninng.librarymanagmentsystem.entity.Loan;


import java.util.ArrayList;
import java.util.List;
public class EventManager {
    private List<Subscriber> subscribers = new ArrayList<>();

    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void notifySubscribers(Loan loan) {
        for (Subscriber subscriber : subscribers) {
            subscriber.update(loan);
        }
    }
}
