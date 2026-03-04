package com.sportradar.fwcsb.domain.game.team;

public class HomeTeam extends AbstractTeam {

    public HomeTeam(final String name) {
        super(name);
    }

    @Override
    public Origin getOrigin() {
        return Origin.HOME;
    }

}
