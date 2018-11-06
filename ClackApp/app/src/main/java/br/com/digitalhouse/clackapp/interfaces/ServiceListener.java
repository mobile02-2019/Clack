package br.com.digitalhouse.clackapp.interfaces;

public interface ServiceListener {

    void onSuccess(Object object);

    void onError(Throwable throwable);

}
