import java.awt.*;

public class Camera2D {

    private final static int DEFAULT_PX = 10;
    private final static int DEFAULT_PY = 10;

    protected double x0, y0;    // Экранные координаты начала мировой системы координат
    protected double pX, pY;    // Единицы масштаба мировой системы координат, выраженные в пикселях
    protected int width, height;         // Разрешение рабочей области окна

    //Переход от мировых координат к экранным (для абсциссы)
    protected int worldToScreenX(double x) {
        return (int) (x0 + pX * x);
    }

    // Переход от мировых координат к экранным (для ординаты)
    protected int worldToScreenY(double y) {
        return (int) (y0 - pY * y);
    }

    //Переход от экранных координат к мировым (для абсциссы)
    protected double screenToWorldX(int x) {
        return (x - x0 + 0.5) / pX;
    }

    //Переход от экранных координат к мировым (для ординаты)
    protected double screenToWorldY(int y) {
        return (y - y0 + 0.5) / pY;
    }

    // Абсцисса левой границы рабочей области окна (в мировых координатах)
    protected double getL() {
        return -x0 / pX;
    }

    //Абсцисса правой границы рабочей области окна (в мировых координатах)
    protected double getR() {
        return (width - x0) / pX;
    }

    protected double getB() {
        return (y0 - height) / pY;
    }

    protected double getT() {
        return y0 / pY;
    }

    private double posX, posY;  // Позиция графического курсора в мировых координатах (для функций MoveTo и LineTo)

    public Camera2D() {
        this.x0 = Panel.DEFAULT_WIDTH / 2;
        this.y0 = Panel.DEFAULT_HEIGHT / 2;
        this.pX = DEFAULT_PX;
        this.pY = DEFAULT_PY;
        this.width = Panel.DEFAULT_WIDTH;
        this.height = Panel.DEFAULT_HEIGHT;
    }

    public void setOrigin(int x, int y){
        x0 = width / 2;
        y0 = height / 2;
    }

    //Очистка рабочей области окна
    public void clear(Graphics g) {
        g.clearRect(0, 0, width, height);
    }

    private void printParameters() {
        System.out.printf("X0: %s, Y0: %s, pX: %s, pY: %s \n", x0, y0, pX, pY);
    }

    /**
     * Данная процедура вызывается при изменении размеров окна
     * В ней задаются значения величин W, H, а также настраиваются значения параметров X0, Y0, px, py таким образом, чтобы обеспечить одинаковые масштабы по координатным осям
     */
    public void setResolution(Dimension dimension) {
        double heightRatio = (double) dimension.height / height;
        double widthRatio = (double) dimension.width / width;

        System.out.printf("height ratio: %s, width ratio: %s \n", heightRatio, widthRatio);
        printParameters();

        x0 = heightRatio * (pY / pX) * x0 + (width / 2) * (widthRatio - heightRatio * pY / pX);
        y0 = heightRatio * y0;
        pX = heightRatio * pY;
        pY = heightRatio * pY;

        height = dimension.height;
        width = dimension.width;
    }

    public void moveOrigin(int deltaX, int deltaY) {
        //System.out.printf("DeltaX: %s DeltaY: %s\n", deltaX, deltaY);
        System.out.printf("X0: %s, Y0: %s, pX: %s, pY: %s \n", x0, y0, pX, pY);
        x0 += deltaX;
        y0 += deltaY;

        posX = 0;
        posY = 0;
    }

    public void scaling(double scale, double xWorld, double yWorld) {
        x0 = x0 - (scale - 1) * pX * xWorld;
        y0 = y0 - (scale - 1) * pY * yWorld;
        pX = scale * pX;
        pY = scale * pY;
        printParameters();

        posX = 0;
        posY = 0;
    }

    /**
     * Перемещение графического курсора (posX, posY)
     * Обратите внимание, что мы действуем в мировых координатах
     */
    public void moveTo(double x, double y) {
        posX = x;
        posY = y;
    }

    /**
     * Отрисовка линии из текущей позиции графического курсора в точку (X, Y) и его перемещение в эту точку
     * Обратите внимание, что мы действуем в мировых координатах
     * При отрисовке линии могут быть использованы WinApi функции
     * ::MoveToEx(dc, Xs, Ys, nullptr) и ::LineTo(dc, Xs, Ys)
     */
    public void lineTo(Graphics g, double x, double y) {
        g.setColor(Color.BLACK);
        g.drawLine(worldToScreenX(posX), worldToScreenY(posY), worldToScreenX(x), worldToScreenY(y));
        moveTo(x, y);
    }

    //Отрисовка координатных осей
    public void drawAxes(Graphics g) {
        moveTo(getL(), 0);
        lineTo(g, getR(), 0);
        moveTo(0, getT());
        lineTo(g, 0, getB());
        moveTo(0,0);
    }

    public void drawGrid(Graphics g) {
        g.setColor(Color.BLUE);
        for (int x = (int) getL(); x < getR(); x++) {
            for (int y = (int)getB(); y < getT(); y++){
                moveTo(getL(), y);
                lineTo(g, getR(), y);
                moveTo(x, getB());
                lineTo(g, x, getT());
            }
        }
        moveTo(0,0);
    }
}
