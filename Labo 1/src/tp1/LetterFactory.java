package tp1;

public final class LetterFactory {
    final static Double maxHeight = 200.0;
    final static Double maxWidth = maxHeight / 2.5;
    final static Double halfMaxHeight = maxHeight / 2;
    final static Double halfMaxWidth = maxWidth / 2;
    final static Double stripeThickness = maxHeight / 8;
    final static Double halfStripeThickness = stripeThickness / 2;

    // TODO
    public static BaseShape create_e() {
        Ellipse mainBody = new Ellipse(maxWidth/2, maxHeight/2);
        Ellipse middleToRemove = new Ellipse((maxWidth - stripeThickness)/2,
                (maxHeight - stripeThickness)/2);
        Rectangle middleStripe = new Rectangle(maxWidth, stripeThickness);
        Rectangle smallRemoval = new Square(stripeThickness).translate(new Point2d(halfMaxWidth-halfStripeThickness,
                stripeThickness));

        mainBody.remove(middleToRemove);
        mainBody.remove(smallRemoval);
        mainBody.add(middleStripe);
        return mainBody;
    }

    // TODO
    public static BaseShape create_a() {
        Ellipse mainBody = new Ellipse(halfMaxWidth, halfMaxHeight);
        Ellipse middleToRemove = new Ellipse((maxWidth - stripeThickness)/2,
                (maxHeight - stripeThickness)/2);
        Rectangle littleALeg = new Rectangle(halfStripeThickness, maxHeight).translate(new
                Point2d(halfMaxWidth-halfStripeThickness/2, 0.0));

        mainBody.remove(middleToRemove);
        mainBody.add(littleALeg);
        return mainBody;
    }

    public static BaseShape create_C() {
        Ellipse mainBody = new Ellipse(halfMaxWidth, halfMaxHeight);
        Ellipse middleToRemove = new Ellipse((maxWidth - stripeThickness)/2,
                (maxHeight - stripeThickness)/2);
        BaseShape rightToRremove = new Rectangle(stripeThickness,
                maxHeight/1.5).translate(new Point2d(halfMaxWidth-halfStripeThickness, 0.0));

        mainBody.remove(middleToRemove);
        mainBody.remove(rightToRremove);
        return mainBody;
    }

    // TODO
    public static BaseShape create_l() {
        Rectangle mainBody = new Rectangle(halfStripeThickness, maxHeight);
        return mainBody;
    }

    // TODO
    public static BaseShape create_i() {
        Circle mainBody = new Circle(halfStripeThickness);
        mainBody.translate(new Point2d(0.0, -halfMaxHeight+halfStripeThickness/2));
        Rectangle bar = new Rectangle(halfStripeThickness, 0.75*maxHeight).translate(new Point2d(0.0,
                0.25*maxHeight/2));

        mainBody.add(bar);
        return mainBody;
    }

    // TODO
    public static BaseShape create_A() {
        Rectangle mainBody = new Rectangle(halfStripeThickness, maxHeight).translate(new Point2d(20.0, 0.0)).
                rotate(Math.toRadians(-11.31));
        Rectangle otherLeg = new Rectangle(halfStripeThickness, maxHeight).translate(new Point2d(-20.0, 0.0)).
                rotate(Math.toRadians(11.31));
        Rectangle middleStripe = new Rectangle(40.0,halfStripeThickness);

        mainBody.add(otherLeg);
        mainBody.add(middleStripe);
        return mainBody;
    }

    // TODO
    public static BaseShape create_V() {
        Rectangle mainBody = new Rectangle(halfStripeThickness, maxHeight).translate(new Point2d(-20.0, 0.0)).
                rotate(Math.toRadians(-11.31));
        Rectangle otherLeg = new Rectangle(halfStripeThickness, maxHeight).translate(new Point2d(20.0, 0.0)).
                rotate(Math.toRadians(11.31));
        mainBody.add(otherLeg);
        return mainBody;
    }

    // TODO
    public static BaseShape create_n() {
        Circle mainBody = new Circle(halfMaxWidth);
        mainBody.translate(new Point2d(0.0, -0.25*maxHeight));

        Circle insideBit = new Circle(halfMaxWidth-halfStripeThickness);
        insideBit.translate(new Point2d(0.0, -0.25*maxHeight));
        mainBody.remove(insideBit);

        Rectangle halfToRemove = new Rectangle(maxWidth, 0.75*maxHeight).translate(new Point2d(0.0,
                0.25*maxHeight/2));
        mainBody.remove(halfToRemove);

        Rectangle leftLeg = new Rectangle(halfStripeThickness, maxHeight).translate(new
                Point2d(-halfMaxWidth+halfStripeThickness/2, 0.0));
        Rectangle rightLeg = new Rectangle(halfStripeThickness, 0.75*maxHeight).translate(new
                Point2d(halfMaxWidth-halfStripeThickness/2, 0.25*maxHeight/2));
        mainBody.add(leftLeg);
        mainBody.add(rightLeg);

        return mainBody;
    }

    // TODO
    public static BaseShape create_r() {
        Circle mainBody = new Circle(halfMaxWidth);
        mainBody.translate(new Point2d(0.0, -0.25*maxHeight));

        Circle insideBit = new Circle(halfMaxWidth-halfStripeThickness);
        insideBit.translate(new Point2d(0.0, -0.25*maxHeight));
        mainBody.remove(insideBit);

        Rectangle halfToRemove = new Rectangle(maxWidth, 0.75*maxHeight).translate(new Point2d(0.0,
                0.25*maxHeight/2));
        mainBody.remove(halfToRemove);

        Rectangle leftLeg = new Rectangle(halfStripeThickness, maxHeight).translate(new
                Point2d(-halfMaxWidth+halfStripeThickness/2, 0.0));
        mainBody.add(leftLeg);

        return mainBody;
    }

    // TODO
    public static BaseShape create_B() {
        BaseShape mainBody = new Circle(maxHeight/4).
                translate(new Point2d(0.0, -0.25*maxHeight));
        mainBody.add(new Circle(maxHeight/4).
                translate(new Point2d(0.0, 0.25*maxHeight)));

        mainBody.remove(new Circle(maxHeight/4 - halfStripeThickness).
                translate(new Point2d(0.0, -0.25*maxHeight)));
        mainBody.remove(new Circle(maxHeight/4 - halfStripeThickness).
                translate(new Point2d(0.0, 0.25*maxHeight)));

        mainBody.remove(new Rectangle(maxWidth, maxHeight).
                translate(new Point2d(-maxWidth/2, 0.0)));

        mainBody.add(new Rectangle(halfStripeThickness, maxHeight));

        return mainBody;
    }
}
