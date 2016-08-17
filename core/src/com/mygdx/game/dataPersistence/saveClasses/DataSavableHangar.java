package com.mygdx.game.dataPersistence.saveClasses;

/**
 * Created by Mechandrius on 05.07.2016.
 */
public class DataSavableHangar extends DataSavable
{
        // arrays needs to be saved as ArrayList ... so java.io.serializable can handle it

        public int nthGame = 0;
        private int slot1;
        private int slot2;

        /**
         * setter for slot1, used in Hangar screen
         * @param id
         */
        public void setSlot1(int id){
                this.slot1 = id;
        }

        /**
         * setter for slot2, used in hangar screen
         * @param id
         */
        public void setSlot2(int id) {
                this.slot2 = id;
        }

        /**
         * getter for slot1
         * @return id of item that is in slot 1
         */
        public int getSlot1(){
                return slot1;
        }

        /**
         * getter for slot2
         * @return id of item that is in slot2
         */
        public int getSlot2() {
                return slot2;
        }
}
