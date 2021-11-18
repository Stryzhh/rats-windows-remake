package entity.weapon;

import entity.Item;

public class Gas  extends Item{

    public Gas(){
        this.entityName = "Gas";
        this.image = null;
        this.hp = 3;
        this.damage = 1;
        this.range = 1;
        this.friendlyFire = true;
        this.isAttackable = false;
    }

}
