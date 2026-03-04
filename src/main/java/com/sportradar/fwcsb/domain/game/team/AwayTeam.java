package com.sportradar.fwcsb.domain.game.team;

public class AwayTeam extends AbstractTeam {

    public AwayTeam(final String name) {
        super(name);
    }

    @Override
    public Origin getOrigin() {
        return Origin.AWAY;
    }
}
