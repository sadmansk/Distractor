// Header File
/*
#ifndef BUBBLE_H
#define BUBBLE_H

#include <QWidget>

namespace Ui {
class Bubble;
}

class Bubble : public QWidget
{
Q_OBJECT

public:
explicit Bubble(QWidget *parent = 0);
virtual QSize sizeHint() const;
~Bubble();
static const int WIDTH = 300, HEIGHT = 300;

protected:
virtual void paintEvent(QPaintEvent *paintEvent);
void mouseMoveEvent(QMouseEvent* event);
void mousePressEvent(QMouseEvent* event);
void mouseReleaseEvent(QMouseEvent* event);
void repositionBubble();

private:
Ui::Bubble *ui;
bool mMoving;
QPoint offset;
};

#endif // BUBBLE_H*/
