package nested.ex_record;


public class Network {

    public void sendMessage(String text){
        NetworkMessage networkMessage = new NetworkMessage(text);
        networkMessage.print();
    }

    private record NetworkMessage(String content) {
        public void print() {
                System.out.println(content);
            }
    }

}
