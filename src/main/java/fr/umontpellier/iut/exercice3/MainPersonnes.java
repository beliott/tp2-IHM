package fr.umontpellier.iut.exercice3;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

@SuppressWarnings("Duplicates")
public class MainPersonnes  {

    private static ObservableList<Personne> lesPersonnes;

    private static ListChangeListener<Personne> unChangementListener;

    public static void main(String[] args) {
        lesPersonnes = FXCollections.observableArrayList(personne -> new Observable[] {personne.ageProperty()});
        unChangementListener = new ListChangeListener<Personne>() {
            @Override
            public void onChanged(Change<? extends Personne> change) {
                change.next();
                if (change.wasAdded())
                    System.out.println(change.getAddedSubList().get(0).getNom() + " a été ajouté");
                if (change.wasRemoved()){
                    System.out.println(change.getRemoved().get(0).getNom() + " a été enlevé");
                }
                if (change.wasUpdated()){
                    System.out.println(change.getList().get(change.getFrom()).getNom() + " a maintenant "+ change.getList().get(change.getFrom()).getAge() + " ans.");
                }
            }
        };
        //lesPersonnes.addListener(unChangementListener);
        //question3()

        ListChangeListener<Personne> plusieursChangementsListener = change -> {
            while (change.next()) {
                if (change.wasAdded()){
                    for ( Personne p : change.getAddedSubList())
                        System.out.println("On ajoute" + p.getNom());
                }
                if (change.wasRemoved()){
                    for ( Personne p : change.getRemoved())
                        System.out.println("On enleve" + p.getNom());
                }
                if (change.wasUpdated()){
                    for (int i = change.getFrom(); i < change.getTo(); i++)
                        System.out.println(change.getList().get(i).getNom() + " a maintenant " + change.getList().get(i).getAge());
                }
            }
            System.out.println("Fin du Listener");
        };

        lesPersonnes.addListener(plusieursChangementsListener);
        question5();
    }

    public static void question1() {
        Personne pierre = new Personne("Pierre", 20);
        Personne paul = new Personne("Paul", 40);
        Personne jacques = new Personne("Jacques", 60);
        lesPersonnes.add(pierre);
        lesPersonnes.add(paul);
        lesPersonnes.add(jacques);
    }

    public static void question2() {
        Personne pierre = new Personne("Pierre", 20);
        Personne paul = new Personne("Paul", 40);
        Personne jacques = new Personne("Jacques", 60);
        lesPersonnes.add(pierre);
        lesPersonnes.add(paul);
        lesPersonnes.add(jacques);
        lesPersonnes.remove(paul);
    }

    public static void question3() {
        Personne pierre = new Personne("Pierre", 20);
        Personne paul = new Personne("Paul", 40);
        Personne jacques = new Personne("Jacques", 60);
        lesPersonnes.add(pierre);
        lesPersonnes.add(paul);
        lesPersonnes.add(jacques);
        paul.setAge(5);
    }

    public static void question5() {
        Personne pierre = new Personne("Pierre", 20);
        Personne paul = new Personne("Paul", 40);
        Personne jacques = new Personne("Jacques", 60);
        lesPersonnes.addAll(pierre, paul, jacques);
        for (Personne p : lesPersonnes)
            p.setAge(p.getAge()+10);
        lesPersonnes.removeAll(paul, pierre);
    }
}

