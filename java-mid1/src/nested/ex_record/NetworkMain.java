package nested.ex_record;


import nested.ex2.Network;

public class NetworkMain {

    public static void main(String[] args) {
        Network network = new Network();
        network.sendMessage("hello java");
    }

}
