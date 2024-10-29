package com.gobierno.seguimiento_egresado.entity;

public interface PageableQuery {

    Integer getPagina();

    Integer getElementosPorPagina();

    String getOrdenadorPor();

    String getEnOrden();

}
