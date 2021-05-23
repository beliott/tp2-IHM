# ![](ressources/logo.jpeg) 
# Introduction aux interfaces homme-machine (IHM) 

### IUT Montpellier-Sète – Département Informatique

* **Cours:** [M2105](http://cache.media.enseignementsup-recherche.gouv.fr/file/25/09/7/PPN_INFORMATIQUE_256097.pdf) - Le support de cours est [consultable ici](https://iutinfomontp-m2105.github.io/Cours).
* **Enseignants:** [Kevin Chapuis](mailto:kevin.chapuis@umontpellier.fr), [Sophie Nabitz](mailto:sophie.nabitz@univ-avignon.fr), [Rémy Portier](mailto:remyportier34@gmail.com)
* Le [forum Piazza]((https://piazza.com/class/kopinpctu3p678) de ce cours pour poser vos questions
* [Email](mailto:sophie.nabitz@univ-avignon.fr) pour une question d'ordre privée concernant le cours.

## TP 2 : gestionnaires d'événement et propriétes

### Création de votre fork du TP

Créez votre fork du TP2 en cliquant sur un lien suivant : [https://classroom.github.com/a/ie3LnUNH](https://classroom.github.com/a/ie3LnUNH) 

Une fois votre fork créé, vous le clonerez classiquement dans IntelliJ.

Tout au long du TP, vous pouvez avoir besoin de **consulter les pages de documentation** de JavaFX, qui sont [disponibles ici](https://openjfx.io/javadoc/16/).

### Exercice 1 - Source d'un événement, premières propriétes et bindings simples

On reprend l'exercice 2 du premier TP qui change la couleur d'un panneau et affiche combien de fois un bouton a été cliqué.<center>
![](ressources/Exo2.png)</center>

1 - Complétez le code qui vous est fourni de façon à ne créer qu'un unique gestionnaire d'événement, que vous associerez ensuite à chacun des trois boutons.

2 - Ajoutez à la classe un attribut *nbFois* de classe `IntegerProperty` et instanciez-le dans un constructeur en utilisant la classe concrète `SimpleIntegerProperty`. Changez le code du gestionnaire d'événement de façon à utiliser cette propriété lors de l'affectation du texte du `Label`.

3 - Ajouter maintenant un attribut *message* de classe `StringProperty`, instanciez-le dans le constructeur en utilisant `SimpleStringProperty`. Dans le gestionnaire d'événement, ce message sera affecté au texte du `Button`. Reprenez le code de façon à ne pas dupliquer de lignes.

4 - Transformez l'affectation du texte du `Label` en un binding sur la propriété `Text` du `Label`, et déplacez ce nouveau code à l'extérieur du gestionnaire d'événement. Vous utiliserez la méthode statique `concat(...)` de `Bindings` (pour concaténer un nombre variable de chaines), et la méthode `asString()` (pour lier avec une `String` correspondant une expression numérique). Vous pouvez consulter la documentation pour cela. Pour l'instant, vous ne vous préoccupez pas de l'état initial du `Label`.

5 - De même, déclarez et instancier un attribut *couleurPanneau* de classe `StringProperty`, mettez à jour cet objet dans le gestionnaire d'événement en utilisant (uniquement) la valeur de la couleur correspondante au bouton choisi, et enfin ajoutez un binding sur la propriété `Style` du panneau.

6 - Extrayez les deux instructions de binding dans une méthode `createBindings()`. Dans cette méthode, déclarez une variable `pasEncoreDeClic` de classe `BooleanProperty` et instanciez-la. Liez cette variable de façon à ce qu'elle change lorsque `nbFois` n'est plus égal à 0. Pour cela, retrouvez la version appropriée de la méthode `equal()` de `Bindings`.<br/>Transformez ensuite le binding sur le `Label` afin de gérer sa valeur initiale en utilisant `Bindings.when`.

### Exercice 2 - Ecouteur de changement

On continue de travailler sur la palette, mais à partir d'un code initial un peu différent. Consultez les fichiers `Palette.java` et `CustomButton.java`.

1 - Ajoutez dans la classe `CustomButton` les 3 méthodes usuelles pour la propriété *nbClics* (pensez à utilisez les facilités de votre IDE...). Ajoutez aussi la méthode `getCouleur()`.

2 - Ajoutez, dans le gestionnaire d'événement, le code qui incrémente le nombre de clics du bouton qui a reçu l'événement. 

3 - Définissez un attribut *nbClicsListener* de type `ChangeListener<Number>` et implémentez-le dans la méthode `start()` de façon à actualiser le `Label` et le style du panneau.<br/>Associez cet écouteur de changement à la propriété *nbClics* de chacun des 3 boutons.

### Exercice 3 - Liste observable

Dans le fichier `MainPersonnes.java`, on va travailler avec une liste de personnes *lesPersonnes* qui peut évoluer, par ajout, suppression et modification d'éléments. Observez la déclaration et l'instanciation de cette liste, objet qui est une propriété `Observable`, ce qui signifie qu'on pourra y attacher des écouteurs, ou la lier à d'autres propriétés.

1 - Dans cette question, vous allez compléter la ligne 17 du fichier `MainPersonnes.java`, de façon à définir un écouteur de changement sur la liste *lesPersonnes*.<br/>
[ListChangeListener](https://docs.oracle.com/javase/8/javafx/api/javafx/collections/ListChangeListener.html) est une interface fonctionnelle dont la méthode à implémenter n'a qu'un argument. Cet argument, de type [ListChangeListener.Change](https://docs.oracle.com/javase/8/javafx/api/javafx/collections/ListChangeListener.Change.html), permet d'accéder aux informations des éléments qui ont changé dans la liste. Pour pouvoir exploiter un changement, il faut (au moins une fois) exécuter la méthode `next()` sur cet argument, et ensuite, suivant le type de changement, utiliser différentes méthodes.<br/>
Pour savoir quel type de changement a eu lieu, vous pouvez utiliser des méthodes booléennes comme `wasAdded()`, `wasRemoved()` et `wasUpdated()`.<br/>
On peut aussi utiliser la méthode `getAddedSubList()` qui retourne la liste de tous les éléments ajoutés.

Pour l'instant, votre code ne traitera qu'un seul ajout à la fois, et se contentera d'afficher "Pierre a été ajouté" (quand la personne dont le nom est "Pierre" a été ajouté...).
Invoquez la méthode `question1()` pour voir le résultat.

2 - On continue avec la suppression d'une personne de la liste, avec un code, à ajouter à l'existant, qui utilise maintenant `getRemoved()` et qui affiche "Pierre a été enlevé". Testez en remplaçant le précédent appel par celui de la méthode `question2()`.

3 - Ecrivez maintenant un code qui devrait réagir à une modification de l'âge en écrivant "Pierre a maintenant ... ans", et testez en appelant maintenant `question3()`. Vous devriez constater que l'écouteur ne réagit pas...

4 - Transformez maintenant l'instanciation de la liste par `FXCollections.observableArrayList(personne -> new Observable[] {personne.ageProperty()});`, qui permet d'exprimer que l'on souhaite écouter les changements sur la propriété *age* de la classe `Personne`. Modifiez le code dans `Personne.java` en conséquence pour pouvoir compiler et testez à nouveau avec `question3()`.

5 - Ajoutez un second écouteur sur la liste *plusieursChangementsListener* qui, cette fois, gère plusieurs changements à la fois et testez avec la méthode `question5()`. Vous pouvez constater que le listener est déclenché plusieurs fois dans le cas d'une modification de l'âge (par exemple en ajoutant un affichage en fin du code du listener).

### Exercice 4 - Low-level binding

On souhaite maintenant faire calculer automatiquement l'âge moyen des personnes dans la liste *lesPersonnes*. Consultez la nouvelle version des classes qui vous sont fournies.

1 - Ecrivez un binding *calculAgeMoyen*, dont vous vous servirez pour que l'attribut *ageMoyen* soit actualisé au fur et à mesure des modifications de la liste *lesPersonnes*. Vous compléterez la classe `Personne` avec les méthodes dont vous pourriez avoir besoin, ainsi que le code de la fonction `main`, avant de tester avec la méthode `question1()`.

2 - Ecrivez un second binding *calculNbParisiens*, qui permettra de connaitre, grâce à l'attribut *nbParisiens*, le nombre de personnes nées à Paris. Testez ensuite avec la méthode `question2()`.

### Exercice 5 - Bindings

On reprend l'exercice du précédent TP qui affiche une interface de login.<br/>
Complétez la méthode `createBindings()` afin que :
- le champ du mot de passe ne soit pas éditable si le nom de l'utilisateur fait moins de 6 caractères,
- le bouton `cancel` ne soit pas cliquable si les deux champs sont vides,
- le bouton `ok` ne soit pas cliquable tant que le mot de passe n'a pas au moins 8 caractères, et ne contient pas au moins une majuscule et un chiffre.