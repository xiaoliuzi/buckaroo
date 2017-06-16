package com.loopperfect.buckaroo.resolver;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.loopperfect.buckaroo.Event;
import com.loopperfect.buckaroo.RecipeIdentifier;
import com.loopperfect.buckaroo.ResolvedDependency;
import com.loopperfect.buckaroo.SemanticVersion;
import org.javatuples.Pair;

import java.util.Map;

public final class ResolvedDependenciesEvent extends Event {

    public final ImmutableMap<RecipeIdentifier, Pair<SemanticVersion, ResolvedDependency>> dependencies;

    private ResolvedDependenciesEvent(final ImmutableMap<RecipeIdentifier, Pair<SemanticVersion, ResolvedDependency>> dependencies) {
        Preconditions.checkNotNull(dependencies);
        this.dependencies = dependencies;
    }

    // TODO: equals, hashCode

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("dependencies", dependencies)
            .toString();
    }

    public static ResolvedDependenciesEvent of(final ImmutableMap<RecipeIdentifier, Pair<SemanticVersion, ResolvedDependency>> dependencies) {
        return new ResolvedDependenciesEvent(dependencies);
    }

    @Deprecated
    public static ResolvedDependenciesEvent of2(final ImmutableMap<RecipeIdentifier, Pair<SemanticVersion, ResolvedDependency>> dependencies) {
        return new ResolvedDependenciesEvent(dependencies.entrySet()
            .stream()
            .map(x -> Maps.immutableEntry(x.getKey(), x.getValue()))
            .collect(ImmutableMap.toImmutableMap(Map.Entry::getKey, Map.Entry::getValue)));
    }
}
