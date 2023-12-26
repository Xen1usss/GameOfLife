public enum Status {
    NONE,  //1
    BORN,  //2
    LIVE,  //1
    DIED;  //2

    public Status step1(int around){
        switch (this){
            case NONE : return (around == 3) ? BORN : NONE;
            case LIVE : return (around <= 3 || around >3) ? DIED : LIVE;
            default:return this;
        }
    }

    public Status step2 (){
        switch (this){
            case BORN : return LIVE;
            case DIED : return NONE;
            default : return this;
        }
    }

    public boolean isCell(){ //проверяем, жива или нет
        return this == LIVE || this == DIED;
    }
}