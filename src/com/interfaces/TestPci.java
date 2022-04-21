package com.interfaces;

public class TestPci {
    public static void main(String[] args) {
        MainBoard board = new MainBoard(2);
        NetCard card = new NetCard();
        board.setPcis(board,card);
        Pci[] pcis = board.getPcis();
        for (Pci pci : pcis) {
            pci.start();
        }
        for (Pci pci : pcis) {
            pci.stop();
        }
    }
}
