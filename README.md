Bibliothèque réalisée par Romain CHUAT et Antonin ROTHE
M1 Informatique

La partie Graf est fonctionnelle et permet d'appeler toutes les méthodes mentionnées par le sujet.
La partie UndirectedGraf est en partie fonctionnelle. Certaines méthodes ne sont pas totalement correctes.
Par exemple la méthode getReverse ne retourne pas l'inverse d'un graphe non dirigé mais toujours l'inverse d'un graphe dirigé.
La méthode getTransitiveClosure ne prends également pas en compte les graphes non dirigés.
Enfin, la méthode getSuccessorsMult peut retourner plus de fois un noeud qu'il n'est censé être présent dans le résultat.