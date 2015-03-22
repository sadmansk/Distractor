#include "bubble.h"
#include "ui_bubble.h"
#include <QtGui>
#include <QDesktopWidget>
#include <QNetworkProxyFactory>
#include <QWebView>
#include <cmath>
#include <iostream>
#include <ctime>

Bubble::Bubble(QWidget *parent) :
    QWidget(parent, Qt::FramelessWindowHint),
    ui(new Ui::Bubble)
{

    QRect rec = QApplication::desktop()->screenGeometry();

    screen_height = rec.height();
    screen_width = rec.width();

    setWindowFlags(Qt::WindowStaysOnTopHint | Qt::CustomizeWindowHint);
    setAttribute(Qt::WA_TranslucentBackground);
    ui->setupUi(this);

    srand(time(NULL));

    int output_x = rand() % (int)(screen_width - WIDTH);
    int output_y = rand() % (int)(screen_height - HEIGHT);

    QPoint placement;
    placement.setX(output_x - pos().x());
    placement.setY(output_y - pos().y());
    this->move(mapToGlobal(placement));

    connect(ui->pushButton, SIGNAL(clicked()), this, SLOT(close()));

    QNetworkProxyFactory::setUseSystemConfiguration (true);
    QWebSettings::globalSettings()->setAttribute(QWebSettings::PluginsEnabled, true);
    QWebSettings::globalSettings()->setAttribute(QWebSettings::AutoLoadImages, true);
    ui->webView->load(QUrl::fromLocalFile("/Users/wojtekswiderski/Documents/Github/Distractor/Distractor/index.html"));
}


void Bubble::closeEvent (QCloseEvent * event){
    ui->webView->load(QUrl("http://google.ca"));
}

Bubble::~Bubble()
{
    delete ui;
}

QSize Bubble::sizeHint() const {
    return QSize(300, 300);
}

void Bubble::paintEvent(QPaintEvent *){
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
    repositionBubble();
}

void Bubble::repositionBubble(){
    if(this->pos().x() + WIDTH / 2 > screen_width / 2){
        QPoint placement;
        placement.setX(screen_width - WIDTH - pos().x());
        placement.setY(0);
        this->move(mapToGlobal(placement));
    }else{
        QPoint placement;
        placement.setX(0 - pos().x());
        placement.setY(0);
        this->move(mapToGlobal(placement));
    }
}
