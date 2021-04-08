

public class Compteur extends Thread {


    public Compteur() {
    }

    @Override
    public void run() {
        int secondes = 0;
        int minutes = 0;
        int heures = 0;
        for (int i = 0 ; i < 24 ; i++) {
            for (int k = 0; k < 60; k++) {
                for (int j = 0; j < 60; j++) {
                    secondes = j;
                    minutes = k;
                    heures = i;
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (secondes > 59) {
                        secondes = 0;
                        minutes++;
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (minutes > 59) {
                            minutes = 0;
                            heures++;
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if (heures > 24) {
                                heures = 0;
                                try {
                                    Thread.sleep(200);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
                Systeme.majDuree();
            }
        }
    }

}
