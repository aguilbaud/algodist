

import jbotsim.Link;
import jbotsim.Node;
import jbotsim.ui.painting.LinkPainter;

import java.awt.*;

/**
 * Cette classe dessine les liens de parenté.
 * Pas besoin d'y toucher.
 */
public class MyPainter implements LinkPainter {

    @Override
    public void paintLink(Graphics2D g2d, Link link){
        MyNode u = (MyNode) link.endpoint(0);
        MyNode v = (MyNode) link.endpoint(1);
        if (u.parent == v)
            paintParentLink(g2d, u, v);
        if (v.parent == u)
            paintParentLink(g2d, v, u);
    }

    private void paintParentLink(Graphics2D g2d, Node child, Node parent) {
        g2d.setStroke(new BasicStroke(3));
        int srcX=(int)child.getX(), srcY=(int)child.getY();
        int destX=(int)parent.getX(), destY=(int)parent.getY();
        g2d.drawLine(srcX, srcY, (srcX+(destX-srcX)), (srcY+(destY-srcY)));
        int x=srcX+4*(destX-srcX)/5;
        int y=srcY+4*(destY-srcY)/5;
        g2d.drawOval(x-2,y-2,4,4);
    }
}
