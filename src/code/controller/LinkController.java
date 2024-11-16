package controller;

import view.LinkView;
import java.util.Scanner;

public class LinkController extends BaseClass<LinkView> {

    public LinkController (Scanner scanner) {
        super(scanner, new LinkView(scanner));
    }

    @Override
    public void run() {
    }
}