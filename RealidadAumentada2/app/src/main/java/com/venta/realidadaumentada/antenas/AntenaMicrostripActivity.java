package com.venta.realidadaumentada.antenas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentOnAttachListener;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.Toast;

import com.google.ar.core.Anchor;
import com.google.ar.core.Config;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.core.Session;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.ArSceneView;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.SceneView;
import com.google.ar.sceneform.Sceneform;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.Renderable;
import com.google.ar.sceneform.rendering.ViewRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.BaseArFragment;
import com.google.ar.sceneform.ux.TransformableNode;
import com.venta.realidadaumentada.R;

import java.lang.ref.WeakReference;

public class AntenaMicrostripActivity extends AppCompatActivity implements
        FragmentOnAttachListener,
        BaseArFragment.OnTapArPlaneListener,
        BaseArFragment.OnSessionConfigurationListener,
        ArFragment.OnViewCreatedListener{

    private ArFragment arFragment;
    public Renderable model;
    private ViewRenderable viewRenderable;
    Button btnAntena, btnAntena2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antena_microstrip);
        btnAntena = findViewById(R.id.buttonAntenaMicro);
        btnAntena2 = findViewById(R.id.buttonAntenaMicro2);

        getSupportFragmentManager().addFragmentOnAttachListener(this);

        if (savedInstanceState == null) {
            if (Sceneform.isSupported(this)) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.arFragment, ArFragment.class, null)
                        .commit();
            }
        }

        btnAntena.setOnClickListener(view -> {
            loadModels();
        });
        btnAntena2.setOnClickListener(view -> {
            loadModels2();
        });

    }
    @Override
    public void onAttachFragment(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment) {
        if (fragment.getId() == R.id.arFragment) {
            arFragment = (ArFragment) fragment;
            arFragment.setOnSessionConfigurationListener(this);
            arFragment.setOnViewCreatedListener(this);
            arFragment.setOnTapArPlaneListener(this);
        }
    }

    @Override
    public void onSessionConfiguration(Session session, Config config) {
        if (session.isDepthModeSupported(Config.DepthMode.AUTOMATIC)) {
            config.setDepthMode(Config.DepthMode.AUTOMATIC);
        }
    }

    @Override
    public void onViewCreated(ArSceneView arSceneView) {
        arFragment.setOnViewCreatedListener(null);

        // Fine adjust the maximum frame rate
        arSceneView.setFrameRateFactor(SceneView.FrameRate.FULL);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    public void loadModels() {
        WeakReference<AntenaMicrostripActivity> weakActivity = new WeakReference<>(this);
        ModelRenderable.builder()
                .setSource(this, R.raw.microstrip_sin_booleano)
                .setIsFilamentGltf(true)
                .setAsyncLoadEnabled(true)
                .build()
                .thenAccept(model -> {
                    AntenaMicrostripActivity activity = weakActivity.get();
                    if (activity != null) {
                        activity.model = model;
                    }
                })
                .exceptionally(throwable -> {
                    Toast.makeText(
                            this, "Unable to load model", Toast.LENGTH_LONG).show();
                    return null;
                });
        ViewRenderable.builder()
                .setView(this, R.layout.view_model_title)
                .build()
                .thenAccept(viewRenderable -> {
                    AntenaMicrostripActivity activity = weakActivity.get();
                    if (activity != null) {
                        activity.viewRenderable = viewRenderable;
                    }
                })
                .exceptionally(throwable -> {
                    Toast.makeText(this, "Unable to load model", Toast.LENGTH_LONG).show();
                    return null;
                });
    }

    public void loadModels2() {
        WeakReference<AntenaMicrostripActivity> weakActivity = new WeakReference<>(this);
        ModelRenderable.builder()
                .setSource(this, R.raw.microstrip)
                .setIsFilamentGltf(true)
                .setAsyncLoadEnabled(true)
                .build()
                .thenAccept(model -> {
                    AntenaMicrostripActivity activity = weakActivity.get();
                    if (activity != null) {
                        activity.model = model;
                    }
                })
                .exceptionally(throwable -> {
                    Toast.makeText(
                            this, "Unable to load model", Toast.LENGTH_LONG).show();
                    return null;
                });
        ViewRenderable.builder()
                .setView(this, R.layout.view_model_title)
                .build()
                .thenAccept(viewRenderable -> {
                    AntenaMicrostripActivity activity = weakActivity.get();
                    if (activity != null) {
                        activity.viewRenderable = viewRenderable;
                    }
                })
                .exceptionally(throwable -> {
                    Toast.makeText(this, "Unable to load model", Toast.LENGTH_LONG).show();
                    return null;
                });
    }
    @Override
    public void onTapPlane(HitResult hitResult, Plane plane, MotionEvent motionEvent) {
        if (model == null || viewRenderable == null) {
            Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show();
            return;
        }
        float minScale = 0.01f; // Cambia este valor según tus necesidades
        float maxScale = 0.1f;
        // Create the Anchor.
        Anchor anchor = hitResult.createAnchor();
        AnchorNode anchorNode = new AnchorNode(anchor);
        anchorNode.setParent(arFragment.getArSceneView().getScene());

        // Create the transformable model and add it to the anchor.
        TransformableNode model = new TransformableNode(arFragment.getTransformationSystem());
        model.setParent(anchorNode);
        model.setRenderable(this.model)

                .animate(true).start();
        model.getScaleController().setMinScale(minScale);
        model.getScaleController().setMaxScale(maxScale);
        model.select();

        Node titleNode = new Node();
        titleNode.setParent(model);
        titleNode.setEnabled(false);
        titleNode.setLocalPosition(new Vector3(0.0f, 0.2f, 0.0f));
        titleNode.setLocalScale(new Vector3(0.2f, 0.2f, 0.2f));
        titleNode.setRenderable(viewRenderable);
        titleNode.setEnabled(false);
    }
}