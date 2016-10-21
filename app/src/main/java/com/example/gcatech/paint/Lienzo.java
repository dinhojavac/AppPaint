package com.example.gcatech.paint;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Lienzo extends View {
    private float startX;
    private float startY;
    private float endX;
    private float endY;
    //la clase path es la que va ir guardando el trazo por el cual voy a ir pintando
    private Path drawPath;
    //es un pincel, Paint de dibujar y canvas
    private Paint drawPaint,canvasPaint;
    //color inicial
    private int paintColor=0xffff0000;
    //canvas
    private Canvas drawCanvas;
    //canvas para guardar, el tipo de archivo a construir
    private Bitmap canvasBitmap;

    String dato;
    public Lienzo(Context context) {
        super(context);
    }

    public Lienzo(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupDrawing();
    }

    //métodos
    private void setupDrawing(){
        //configuración del área sobre la que pintar
        drawPath=new Path();
        drawPaint=new Paint();
        //color inicial
        drawPaint.setColor(paintColor);
        //el tema de los trazos sean mas suaves con los strokes
        drawPaint.setAntiAlias(true);
        //es el ancho
        //drawPaint.setStrokeWidth(20);
        //pintar solamente trazos
        drawPaint.setStyle(Paint.Style.STROKE);
        //pintar redondeado
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
        //es el objeto, dither flag permite pintar mas difuminado
        canvasPaint=new Paint(Paint.DITHER_FLAG);

    }

    //tamano asignado a la vista- son funciones que vienen
    //es una vista que cuando la creas pone las caracteristicas
    @Override
    protected void onSizeChanged(int w,int h,int oldw,int oldh){
        super.onSizeChanged(w,h,oldw,oldh);
        //objetos que va obtener
        canvasBitmap=Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
        //se obtiene un nuevo canvas dentro para pintar sobre el
        drawCanvas=new Canvas(canvasBitmap);
    }

    //Pinta la vista. Será llamado desde el OnTouchEvent
    //se repinta sobre el, se repinta el canvas
    @Override
    protected void onDraw(Canvas canvas){
        //es un dibujo en memoria con un formato, que trabajara con canvasPaint
        //mapa de bits
        canvas.drawBitmap(canvasBitmap,0,0,canvasPaint);
        canvas.drawPath(drawPath,drawPaint);
        canvas.drawLine(startX, startY, endX, endY, drawPaint);


    }





    //funcion Touch-registra los touch de usuario
    //va controlar los eventos del usuario
    Intent dialogo;
    public boolean onTouchEvent(MotionEvent event){
        /*float touchX=event.getX();
        float touchY=event.getY();
        float endX=event.getX();
        float endY=event.getY();*/

        switch (event.getAction()){
            //cuando se pulsa con el dedo
            case MotionEvent.ACTION_DOWN:
                //drawPath.moveTo(touchX,touchY);
                startX = event.getX();
                startY = event.getY();
                invalidate();
                break;
            //moviendo el dedo
            case MotionEvent.ACTION_MOVE:
                //construye una linea por el movimiento del dedo
                //drawPath.lineTo(endX,endY);
                endX = event.getX();
                endY = event.getY();
                invalidate();
                break;
            //cuando se levanta
            case MotionEvent.ACTION_UP:
                //para al final hacer el ultimo trazito de la linea
                //drawPath.lineTo(endX,endY);
                //y para pasarle al canvas para que pueda pintar tdo el seguimiento del dedo
                endX = event.getX();
                endY = event.getY();
                drawCanvas.drawLine(startX, startY, endX, endY, drawPaint);

                //drawCanvas.drawPath(drawPath,drawPaint);
                //drawPath.reset();
                float x = ((endX - startX)/2)+startX;
                float y =  ((endY - startY)/2)+startY;
                dialogoP(x, y);




                invalidate();

                break;
            default:
                return false;
        }
        //repintar
        //es llamar a repintar el canvas, cada vez que se esta haciendo en touch event se esta entrando o llamando a repintar el canvas
        //sin parar, para que vaya actualizandolo tdo
        //esta función invalidate nos va permitir sobreescribir el  metodo onDraw() que hay que sobreescribirlo en todos los view
        invalidate();
        return true;
    }

    //funcion pintar color
    //actualiza color

    public void setColor(String newColor){
        invalidate();
        //coloca el nuevo color
        paintColor= Color.parseColor(newColor);
        drawPaint.setColor(paintColor);
    }
    public Lienzo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //metodo dialogo-funcional
    /*
    public void dialogo(){
        AlertDialog.Builder b=new AlertDialog.Builder(getContext());
        b.setTitle("Titulo");
        b.setMessage("el mensaje para el usuario");
        b.setPositiveButton("Ok",
                new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog,int which){
                        //listener.onPossitiveButtonClick();
                        Toast.makeText(getContext(),R.string.ok,Toast.LENGTH_SHORT).show();
                    }
                });
        b.setNegativeButton(android.R.string.cancel,null);
        //Dialog d=b.create();
        b.show();
    }*/
    //metodo dialogo 2-funcional

    public void dialogoP(final float x, final float y){

        LayoutInflater inflater = (LayoutInflater)   getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialoglayoutPR = inflater.inflate(R.layout.dialogo, null);
        final EditText edtxtMedida = (EditText) dialoglayoutPR.findViewById(R.id.Valor); // dialoglayoutPR.Valor
        final Button btnOk  = (Button) dialoglayoutPR.findViewById(R.id.Enviar);
        final Button btnCancel = (Button) dialoglayoutPR.findViewById(R.id.Reset);
        final TextView txtMessage = (TextView) dialoglayoutPR.findViewById(R.id.InfoText);

        final AlertDialog.Builder b = new AlertDialog.Builder(getContext());                                //creamos un objeto de la clase AlertDialog a través de la clase Builder
        AlertDialog.Builder builder = b.setCancelable(false);
        b.setTitle("IPA");
        b.setView(dialoglayoutPR);
        txtMessage.getText();
        edtxtMedida.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(edtxtMedida.getText().toString().length()==5)
                {
                    edtxtMedida.setError("Supera el limit");
                    
                }
                return false;
            }
        });

        final AlertDialog showDialogo = b.show();

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                dato = edtxtMedida.getText().toString().trim();
                //vlor=Integer.parseInt(dato);
                //listener.onPossitiveButtonClick();
                if(dato.length()!=0){
                    Toast.makeText(getContext(),R.string.ok,Toast.LENGTH_LONG).show();
                    drawCanvas.drawText(dato, x, y, drawPaint);
                    showDialogo.dismiss();
                }

                else{
                    //Toast.makeText(getContext(),R.string.cancelar,Toast.LENGTH_LONG).show();
                    edtxtMedida.setError("Debe Ingresar un Valor");


                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                showDialogo.dismiss();
            }
        });



    }







}
