package com.example.juegopreguntas4;

public class Pregunta {

    //Clase pregunta

    String pregunta, resp1, resp2, resp3, resp4, respCorrecta, autor;
    boolean respondida;

    public Pregunta() {

        pregunta = "vacio";
        resp1 = "vacio";
        resp2 = "vacio";
        resp3 = "vacio";
        resp4 = "vacio";
        respCorrecta = "vacio";
        respondida = false;
        autor = "vacio";
    }

    public Pregunta(String p, String r1, String r2, String r3, String r4, String rc, String a) {

        pregunta = p;
        resp1 = r1;
        resp2 = r2;
        resp3 = r3;
        resp4 = r4;
        respCorrecta = rc;
        respondida = false;
        autor = a;
    }

    public String getPregunta() {

        return pregunta;
    }

    public void setPregunta(String pregunta) {

        this.pregunta = pregunta;
    }

    public String getResp1() {

        return resp1;
    }

    public void setResp1(String resp1) {

        this.resp1 = resp1;
    }

    public String getResp2() {

        return resp2;
    }

    public void setResp2(String resp2) {

        this.resp2 = resp2;
    }

    public String getResp3() {

        return resp3;
    }

    public void setResp3(String resp3) {

        this.resp3 = resp3;
    }

    public String getResp4() {

        return resp4;
    }

    public void setResp4(String resp4) {

        this.resp4 = resp4;
    }

    public String getRespCorrecta() {

        return respCorrecta;
    }

    public void setRespCorrecta(String respCorrecta) {

        this.respCorrecta = respCorrecta;
    }

    public boolean isRespondida() {

        return respondida;
    }

    public void setRespondida(boolean respondida) {

        this.respondida = respondida;
    }

    public String getAutor() {

        return autor;
    }

    public void setAutor(String autor) {

        this.autor = autor;
    }
}
