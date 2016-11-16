package projet_dcer_prt;

public class Info_employe {
    
public String matricule, mdp;

public Info_employe (String matricule, String mdp) {
    
    this.matricule = matricule;
    this.mdp = mdp;
    
}

@Override
public String toString() {
    return "Info_employe{ matricule = " + matricule + ", mot de passe = " + mdp + "}";
}
    
}