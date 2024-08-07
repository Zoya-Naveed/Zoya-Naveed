package com.github.group37.roadmap.other;

import com.github.group37.roadmap.percistance.models.RevisionResourceDao;

import java.util.ArrayList;
import java.util.Optional;

public class Roadmap {
    private String name;

    private ArrayList<Optional<RevisionResourceDao>> revisionResourceDaos;

    public Roadmap(String name, ArrayList<Optional<RevisionResourceDao>> revisionResourceDaos) {
        this.name = name;
        this.revisionResourceDaos = revisionResourceDaos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Optional<RevisionResourceDao>> getRevisionResourceDaos() {
        return revisionResourceDaos;
    }

    public void setRevisionResourceDaos(ArrayList<Optional<RevisionResourceDao>> revisionResourceDaos) {
        this.revisionResourceDaos = revisionResourceDaos;
    }
}
