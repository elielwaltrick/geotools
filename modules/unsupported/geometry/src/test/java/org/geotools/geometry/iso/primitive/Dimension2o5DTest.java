/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2004-2008, Open Source Geospatial Foundation (OSGeo)
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */
package org.geotools.geometry.iso.primitive;

import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import org.geotools.geometry.GeometryBuilder;
import org.geotools.geometry.iso.coordinate.GeometryFactoryImpl;
import org.geotools.geometry.iso.coordinate.LineStringImpl;
import org.geotools.geometry.iso.coordinate.PositionImpl;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.PositionFactory;
import org.opengis.geometry.coordinate.Position;
import org.opengis.geometry.primitive.Curve;
import org.opengis.geometry.primitive.CurveSegment;
import org.opengis.geometry.primitive.Ring;
import org.opengis.geometry.primitive.Surface;

public class Dimension2o5DTest extends TestCase {

    public void testMain() {

        GeometryBuilder builder = new GeometryBuilder(DefaultGeographicCRS.WGS84_3D);

        this._testCurve1(builder);
        this._testSurface(builder);
    }

    private void _testCurve1(GeometryBuilder builder) {

        CurveImpl curve1 = (CurveImpl) this._createCurve1(builder);
        this.printCurve(curve1);
        CurveImpl curve2 = (CurveImpl) this._createCurve2(builder);
        this.printCurve(curve2);

        //		GeometryImpl g = (GeometryImpl) curve1.intersection(curve2);
        //		//System.out.println("Intersection of curves: " + g);

    }

    private void printCurve(CurveImpl curve1) {
        // System.out.print("\n******************* CURVE");
        // System.out.println("\n" + curve1);
        // System.out.println("\nCoordinate Dimension: " + curve1.getCoordinateDimension());
        // System.out.println("\nDimension: " + curve1.getDimension(null));
        //// System.out.println("\nDimension Model: " +
        // curve1.getFeatGeometryFactory().getDimensionModel());
        // System.out.println("Length of Curve is " + curve1.length());
        // System.out.println("Envelope of the Curve is " +  curve1.getEnvelope());
    }

    private void _testSurface(GeometryBuilder builder) {
        SurfaceImpl surface = (SurfaceImpl) this._createSurface1(builder);
        // System.out.print("\n******************* SURFACE GENERATED BY SURFACEBOUNDARY");
        // System.out.println("\n" + surface);
        // System.out.println("\n Coordinate Dimension: " + surface.getCoordinateDimension());
        // System.out.println("\n Dimension: " + surface.getDimension(null));
        //// System.out.println("\n Dimension Model: " +
        // surface.getFeatGeometryFactory().getDimensionModel());
        // System.out.println("\n Envelope: " + surface.getEnvelope());
    }

    private Curve _createCurve1(GeometryBuilder builder) {
        GeometryFactoryImpl tCoordFactory = (GeometryFactoryImpl) builder.getGeometryFactory();
        PrimitiveFactoryImpl tPrimFactory = (PrimitiveFactoryImpl) builder.getPrimitiveFactory();
        PositionFactory pf = builder.getPositionFactory();

        PositionImpl p1 = new PositionImpl(pf.createDirectPosition(new double[] {-50, 0, 0}));
        PositionImpl p2 = new PositionImpl(pf.createDirectPosition(new double[] {-30, 30, 10}));
        PositionImpl p3 = new PositionImpl(pf.createDirectPosition(new double[] {0, 50, 20}));
        PositionImpl p4 = new PositionImpl(pf.createDirectPosition(new double[] {30, 30, 10}));
        PositionImpl p5 = new PositionImpl(pf.createDirectPosition(new double[] {50, 0, 0}));

        LineStringImpl line1 = null;

        ArrayList<Position> positionList = new ArrayList<Position>();
        positionList.add(p1);
        positionList.add(p2);
        positionList.add(p3);
        positionList.add(p4);
        positionList.add(p5);
        try {
            line1 = tCoordFactory.createLineString(positionList);
        } catch (IllegalArgumentException e) {
            // System.out.println(e);
        }

        /* Set parent curve for LineString */
        ArrayList<CurveSegment> tLineList = new ArrayList<CurveSegment>();
        tLineList.add(line1);

        // PrimitiveFactory.createCurve(List<CurveSegment>)
        CurveImpl curve1 = tPrimFactory.createCurve(tLineList);

        // Set curve for further LineString tests
        line1.setCurve(curve1);

        return curve1;
    }

    private Curve _createCurve2(GeometryBuilder builder) {
        GeometryFactoryImpl tCoordFactory = (GeometryFactoryImpl) builder.getGeometryFactory();
        PrimitiveFactoryImpl tPrimFactory = (PrimitiveFactoryImpl) builder.getPrimitiveFactory();
        PositionFactory pf = builder.getPositionFactory();

        PositionImpl p1 = new PositionImpl(pf.createDirectPosition(new double[] {10, 0, 100}));
        PositionImpl p2 = new PositionImpl(pf.createDirectPosition(new double[] {50, 30, 100}));

        LineStringImpl line1 = null;

        ArrayList<Position> positionList = new ArrayList<Position>();
        positionList.add(p1);
        positionList.add(p2);
        try {
            line1 = tCoordFactory.createLineString(positionList);
        } catch (IllegalArgumentException e) {
            // System.out.println("e");
        }

        /* Set parent curve for LineString */
        ArrayList<CurveSegment> tLineList = new ArrayList<CurveSegment>();
        tLineList.add(line1);

        // PrimitiveFactory.createCurve(List<CurveSegment>)
        CurveImpl curve1 = tPrimFactory.createCurve(tLineList);

        // Set curve for further LineString tests
        line1.setCurve(curve1);

        return curve1;
    }

    private Surface _createSurface1(GeometryBuilder builder) {
        GeometryFactoryImpl tCoordFactory = (GeometryFactoryImpl) builder.getGeometryFactory();
        PrimitiveFactoryImpl tPrimFactory = (PrimitiveFactoryImpl) builder.getPrimitiveFactory();
        PositionFactory pf = builder.getPositionFactory();

        List<DirectPosition> directPositionList = new ArrayList<DirectPosition>();
        directPositionList.add(pf.createDirectPosition(new double[] {20, 10, 100}));
        directPositionList.add(pf.createDirectPosition(new double[] {40, 10, 100}));
        directPositionList.add(pf.createDirectPosition(new double[] {50, 40, 100}));
        directPositionList.add(pf.createDirectPosition(new double[] {30, 50, 100}));
        directPositionList.add(pf.createDirectPosition(new double[] {10, 30, 100}));
        directPositionList.add(pf.createDirectPosition(new double[] {20, 10, 100}));

        Ring exteriorRing = tPrimFactory.createRingByDirectPositions(directPositionList);
        List<Ring> interiors = new ArrayList<Ring>();

        SurfaceBoundaryImpl surfaceBoundary1 =
                tPrimFactory.createSurfaceBoundary(exteriorRing, interiors);

        Surface surface2 = tPrimFactory.createSurface(surfaceBoundary1);

        return surface2;
    }

    public Surface _createSurface2(GeometryBuilder builder) {
        GeometryFactoryImpl tCoordFactory = (GeometryFactoryImpl) builder.getGeometryFactory();
        PrimitiveFactoryImpl tPrimFactory = (PrimitiveFactoryImpl) builder.getPrimitiveFactory();
        PositionFactory pf = builder.getPositionFactory();

        List<DirectPosition> directPositionList = new ArrayList<DirectPosition>();
        directPositionList.add(pf.createDirectPosition(new double[] {20, 10, 100}));
        directPositionList.add(pf.createDirectPosition(new double[] {40, 10, 100}));
        directPositionList.add(pf.createDirectPosition(new double[] {50, 40, 100}));
        directPositionList.add(pf.createDirectPosition(new double[] {30, 50, 100}));
        directPositionList.add(pf.createDirectPosition(new double[] {10, 30, 100}));
        directPositionList.add(pf.createDirectPosition(new double[] {20, 10, 100}));

        Ring exteriorRing = tPrimFactory.createRingByDirectPositions(directPositionList);
        List<Ring> interiors = new ArrayList<Ring>();

        SurfaceBoundaryImpl surfaceBoundary1 =
                tPrimFactory.createSurfaceBoundary(exteriorRing, interiors);

        Surface surface2 = tPrimFactory.createSurface(surfaceBoundary1);

        return surface2;
    }
}