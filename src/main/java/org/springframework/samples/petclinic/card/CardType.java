package org.springframework.samples.petclinic.card;


public enum CardType {
    EXTRACCION_RECURSOS {
        @Override
        public boolean isResource() {
            return true;
        }
    },
    FORJA {
        @Override
        public boolean isForging() {
            return true;
        }
    }, DEFENSA {
        @Override
        public boolean isDefense() {
            return true;
        }
    }, RECIBIR_AYUDA {
        @Override
        public boolean isHelp() {
            return true;
        }
    }, ESPECIAL {
        @Override
        public boolean isSpecial() {
            return true;
        }
    };

    public boolean isResource() {return false;}

    public boolean isSpecial() {return false;}

    public boolean isHelp() {return false;}

    public boolean isDefense() {return false;}

    public boolean isForging() {return false;}
}
