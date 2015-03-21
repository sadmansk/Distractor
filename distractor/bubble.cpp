// C++ File#include <QWidget>

#include "bubble.h"
#include "ui_bubble.h"
#include <QtGui>

Bubble::Bubble(QWidget *parent) :
QWidget(parent, Qt::FramelessWindowHint),
ui(new Ui::Bubble)
{
setAttribute(Qt::WA_TranslucentBackground);
ui->setupUi(this);
}

Bubble::~Bubble()
{
delete ui;
}

QSize Bubble::sizeHint() const {
return QSize(300, 300);
}

void Bubble::paintEvent(QPaintEvent *paintEvent){
QPainter painter(this);
painter.setRenderHint(QPainter::Antialiasing);
painter.setPen(Qt::NoPen);
painter.setBrush(QColor(255, 255, 255, 255));
painter.drawEllipse(0, 0, width(), height());
}

void Bubble::mousePressEvent(QMouseEvent* event)
{
offset = event->pos();
if(event->button() == Qt::LeftButton)
{
mMoving = true;
}
}

void Bubble::mouseMoveEvent(QMouseEvent* event)
{
if( event->buttons().testFlag(Qt::LeftButton) && mMoving)
{
this->move(mapToParent(event->pos() - offset));
}
}

void Bubble::mouseReleaseEvent(QMouseEvent* event)
{
if(event->button() == Qt::LeftButton)
{
mMoving = false;
}
}

void Bubble::repositionBubble(){

}
