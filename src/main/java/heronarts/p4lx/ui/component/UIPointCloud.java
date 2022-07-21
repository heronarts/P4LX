/**
 * Copyright 2013- Mark C. Slee, Heron Arts LLC
 *
 * This file is part of the LX Studio software library. By using
 * LX, you agree to the terms of the LX Studio Software License
 * and Distribution Agreement, available at: http://lx.studio/license
 *
 * Please note that the LX license is not open-source. The license
 * allows for free, non-commercial use.
 *
 * HERON ARTS MAKES NO WARRANTY, EXPRESS, IMPLIED, STATUTORY, OR
 * OTHERWISE, AND SPECIFICALLY DISCLAIMS ANY WARRANTY OF
 * MERCHANTABILITY, NON-INFRINGEMENT, OR FITNESS FOR A PARTICULAR
 * PURPOSE, WITH RESPECT TO THE SOFTWARE.
 *
 * ##library.name##
 * ##library.sentence##
 * ##library.url##
 *
 * @author      ##author##
 * @modified    ##date##
 * @version     ##library.prettyVersion## (##library.version##)
 */

package heronarts.p4lx.ui.component;

import com.google.gson.JsonObject;

import heronarts.lx.LX;
import heronarts.lx.LXEngine;
import heronarts.lx.LXSerializable;
import heronarts.lx.model.LXPoint;
import heronarts.lx.parameter.BoundedParameter;
import heronarts.p4lx.P4LX;
import heronarts.p4lx.ui.UI;
import heronarts.p4lx.ui.UI3dComponent;
import processing.core.PConstants;
import processing.core.PGraphics;

/**
 * Draws a cloud of points in the layer
 */
public class UIPointCloud extends UI3dComponent implements LXSerializable {

  protected final P4LX lx;

  public final BoundedParameter pointSize = new BoundedParameter("Point Size", 2, 1, 10)
  .setDescription("Size of points in the UI");

  protected float[] pointSizeAttenuation = null;

  protected boolean auxiliary = false;

  /**
   * Point cloud for everything in the LX instance
   *
   * @param lx LX instance
   */
  public UIPointCloud(P4LX lx) {
    this.lx = lx;
  }

  /**
   * Sets the size of points
   *
   * @param pointSize Point size
   * @return this
   */
  public UIPointCloud setPointSize(float pointSize) {
    this.pointSize.setValue(pointSize);
    return this;
  }

  public UIPointCloud setAuxiliary(boolean auxiliary) {
    this.auxiliary = auxiliary;
    return this;
  }

  /**
   * Disable point size attenuation
   *
   * @return this
   */
  public UIPointCloud disablePointSizeAttenuation() {
    this.pointSizeAttenuation = null;
    return this;
  }

  /**
   * Sets point size attenuation, fn = 1/sqrt(constant + linear*d + quadratic*d^2)
   *
   * @param a Constant factor
   * @param b Linear factor
   * @param c Quadratic factor
   * @return this
   */
  public UIPointCloud setPointSizeAttenuation(float a, float b, float c) {
    if (this.pointSizeAttenuation == null) {
      this.pointSizeAttenuation = new float[3];
    }
    this.pointSizeAttenuation[0] = a;
    this.pointSizeAttenuation[1] = b;
    this.pointSizeAttenuation[2] = c;
    return this;
  }

  @Override
  protected void onDraw(UI ui, PGraphics pg) {
    LXEngine.Frame frame = this.lx.getUIFrame();
    int[] colors = this.auxiliary ? frame.getAuxColors() : frame.getColors();
    pg.noFill();
    pg.strokeWeight(this.pointSize.getValuef());
    pg.beginShape(PConstants.POINTS);
    for (LXPoint p : frame.getModel().points) {
      pg.stroke(colors[p.index]);
      pg.vertex(p.x, p.y, p.z);
    }
    pg.endShape();
    pg.strokeWeight(1);
  }

  private static final String KEY_POINT_SIZE = "pointSize";

  @Override
  public void save(LX lx, JsonObject object) {
    object.addProperty(KEY_POINT_SIZE, this.pointSize.getValue());
  }

  @Override
  public void load(LX lx, JsonObject object) {
    LXSerializable.Utils.loadDouble(this.pointSize, object, KEY_POINT_SIZE);

  }
}
