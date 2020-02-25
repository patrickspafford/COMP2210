public class Letter {
   public int order;
   public String value;
   public boolean visited;
   public Letter(String value) {
      this.value = value;
      this.visited = false;
      this.order = 0;
      
   }
   public void reset() {
      order = 0;
      visited = false;
   
   }
   @Override
   public String toString() {
   
      return "(" + value + ", " + visited + ")";
   
   }



}