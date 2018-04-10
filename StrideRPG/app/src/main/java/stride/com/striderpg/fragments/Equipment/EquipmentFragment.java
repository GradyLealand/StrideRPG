package stride.com.striderpg.fragments.Equipment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;

import stride.com.striderpg.R;
import stride.com.striderpg.global.G;
import stride.com.striderpg.rpg.Constants;
import stride.com.striderpg.rpg.models.Item.Item;

/**
 * Inventory Fragment that displays a Players current equipment
 * and what they have equipped as well as their statistics gained
 * from the equipment.
 */
public class EquipmentFragment extends Fragment {

    /**
     * EquipmentFragment Logging tag.
     */
    private static final String TAG = "EquipmentFragment";

    /**
     * RecyclerView that will hold each individual Item CardView.
     */
    RecyclerView equipmentRecyclerView;

    /**
     * EquipmentGenerator used to grab the current Items from
     * the active players Equipment slots HashMap.
     */
    EquipmentGenerator generator = new EquipmentGenerator();

    /**
     * Player profile image.
     */
    ImageView playerImage;

    /**
     * Player username.
     */
    TextView playerName;

    /**
     * Player current skill points amount.
     */
    TextView playerSkillPoints;

    /**
     * Player current strength value.
     */
    TextView playerStrength;

    /**
     * Player current vitality value.
     */
    TextView playerVitality;

    /**
     * Player current speed value.
     */
    TextView playerSpeed;

    /**
     * Player level up strength button.
     */
    Button strengthLevelButton;

    /**
     * Player level up vitality button.
     */
    Button vitalityLevelButton;

    /**
     * Player level up speed button.
     */
    Button speedLevelButton;

    /**
     * Player total strength points with gear bonus.
     */
    TextView strengthTotalPoints;

    /**
     * Player total vitality points with gear bonus.
     */
    TextView vitalityTotalPoints;

    /**
     * Player total speed points with gear bonus.
     */
    TextView speedTotalPoints;

    /**
     * EquipmentAdapter for inflating and instantiating each CardView
     * with Active Players current equipment information.
     */
    EquipmentAdapter adapter;

    /**
     * Empty Public Constructor.
     */
    public EquipmentFragment() { }

    @Override
    public void onStart() {
        super.onStart();

        // Set each element present in the Equipment fragment initially.
        getDashboardElements();

        // Initial Player Equipment information setup.
        buildEquipmentInitial();

        // Setup PropertyChangeListeners to enable updates in real time.
        buildPropertyChangeListeners();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_equipment, container, false);

        // Set the RecyclerView to the inflated rootView rv (RecyclerView).
        equipmentRecyclerView = rootView.findViewById(R.id.rv);
        equipmentRecyclerView.setHasFixedSize(true);

        // Set the LayoutManager used in the Quests.
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        equipmentRecyclerView.setLayoutManager(llm);

        // Create the Adapter used to inflate each Quest CardView.
        adapter = new EquipmentAdapter(generator.getItems());
        equipmentRecyclerView.setAdapter(adapter);
        return rootView;
    }

    private void getDashboardElements() {
        try {
            playerImage = Objects.requireNonNull(getView()).findViewById(R.id.playerImageText);
            playerName = getView().findViewById(R.id.playerNameText);
            playerSkillPoints = getView().findViewById(R.id.playerSkillPointsText);
            playerStrength = getView().findViewById(R.id.strengthBaseValue);
            playerVitality = getView().findViewById(R.id.vitalityBaseValue);
            playerSpeed = getView().findViewById(R.id.speedBaseValue);
            strengthLevelButton = getView().findViewById(R.id.strengthLevelButton);
            vitalityLevelButton = getView().findViewById(R.id.vitalityLevelButton);
            speedLevelButton = getView().findViewById(R.id.speedLevelButton);
            strengthTotalPoints = getView().findViewById(R.id.strengthTotalValue);
            vitalityTotalPoints = getView().findViewById(R.id.vitalityTotalValue);
            speedTotalPoints = getView().findViewById(R.id.speedTotalValue);
        } catch (Exception e) {
            Log.e(TAG, "getDashboardElements:error:", e);
        }
    }

    private void buildEquipmentInitial() {
        playerName.setText(G.activePlayer.getUsername());
        playerSkillPoints.setText(String.format(G.locale, "Skill Points: %d", G.activePlayer.getSkills().getSkillPoints()));
        playerStrength.setText(String.format(G.locale, "%d", G.activePlayer.getSkills().getStrength()));
        playerVitality.setText(String.format(G.locale, "%d", G.activePlayer.getSkills().getVitality()));
        playerSpeed.setText(String.format(G.locale, "%d", G.activePlayer.getSkills().getSpeed()));

        // Set the skill level buttons enabled property based on initial player skill points.
        strengthLevelButton.setEnabled(G.activePlayer.getSkills().getSkillPoints() > 0);
        vitalityLevelButton.setEnabled(G.activePlayer.getSkills().getSkillPoints() > 0);
        speedLevelButton.setEnabled(G.activePlayer.getSkills().getSkillPoints() > 0);

        // Build the total points w/ gear bonuses.
        Integer totalStrength = G.activePlayer.getEquipment().getStrengthBoost() + G.activePlayer.getSkills().getStrength();
        strengthTotalPoints.setText(String.format(G.locale, "%s", totalStrength.toString()));

        Integer totalVitality = G.activePlayer.getEquipment().getVitalityBoost() + G.activePlayer.getSkills().getVitality();
        vitalityTotalPoints.setText(String.format(G.locale, "%s", totalVitality.toString()));

        Integer totalSpeed = G.activePlayer.getEquipment().getSpeedBoost() + G.activePlayer.getSkills().getSpeed();
        speedTotalPoints.setText(String.format(G.locale, "%s", totalSpeed.toString()));

        // Build the onClick listeners for each skill level button.
        strengthLevelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                G.activePlayer.getSkills().levelUpStrength();
                G.activePlayer.getSkills().removePoint();
            }
        });
        vitalityLevelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                G.activePlayer.getSkills().levelUpVitality();
                G.activePlayer.getSkills().removePoint();
            }
        });
        speedLevelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                G.activePlayer.getSkills().levelUpSpeed();
                G.activePlayer.getSkills().removePoint();
            }
        });
    }

    /**
     * Update the Skill Points text view with the new skill points amount.
     */
    private void updateSkillPointsText(Integer newSkillPoints) {
        playerSkillPoints.setText(String.format(G.locale, "Skill Points: %d", newSkillPoints));
    }

    /**
     * Update the Players base strength with new strength amount.
     */
    private void updateStrengthBase(Integer newStrength) {
        playerStrength.setText(String.format(G.locale, "%d", newStrength));
    }

    /**
     * Update the Players base vitality with new vitality amount.
     */
    private void updateVitalityBase(Integer newVitality) {
        playerVitality.setText(String.format(G.locale, "%d", newVitality));
    }

    /**
     * Update the Players base speed with new speed amount.
     */
    private void updateSpeedBase(Integer newSpeed) {
        playerSpeed.setText(String.format(G.locale, "%d", newSpeed));
    }

    private void updateTotalStrength(Integer newTotalStrength) {
        strengthTotalPoints.setText(String.format(G.locale, "%d", newTotalStrength));
    }

    private void updateTotalVitality(Integer newTotalVitality) {
        vitalityTotalPoints.setText(String.format(G.locale, "%d", newTotalVitality));
    }

    private void updateTotalSpeed(Integer newTotalSpeed) {
        speedTotalPoints.setText(String.format(G.locale, "%d", newTotalSpeed));
    }

    private void checkEnableSkillButtons(Integer newSkillPoints) {
        // Set the skill level buttons enabled property based on initial player skill points.
        strengthLevelButton.setEnabled(newSkillPoints > 0);
        vitalityLevelButton.setEnabled(newSkillPoints > 0);
        speedLevelButton.setEnabled(newSkillPoints > 0);
    }

    private void buildPropertyChangeListeners() {
        // Add new PropertyChangeListener implementations that control
        // the ui elements being changed when internal values are changed.
        G.activePlayer.getSkills().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                switch (propertyChangeEvent.getPropertyName()) {
                    // When a Player levels up and gains skill points.
                    case Constants.PROPERTY_SKILL_POINTS:
                        updateSkillPointsText((Integer)propertyChangeEvent.getNewValue());
                        checkEnableSkillButtons((Integer)propertyChangeEvent.getNewValue());
                        break;

                    // When a Player levels up their Strength skill.
                    case Constants.PROPERTY_SKILL_STRENGTH:
                        Integer newBaseStrength = (Integer)propertyChangeEvent.getNewValue();
                        updateStrengthBase(newBaseStrength);
                        updateTotalStrength(newBaseStrength + G.activePlayer.getEquipment().getStrengthBoost());
                        break;

                    // When a Player levels up their Vitality skill.
                    case Constants.PROPERTY_SKILL_VITALITY:
                        Integer newBaseVitality = (Integer)propertyChangeEvent.getNewValue();
                        updateVitalityBase(newBaseVitality);
                        updateTotalVitality(newBaseVitality + G.activePlayer.getEquipment().getVitalityBoost());
                        break;

                    // When a Player levels up their Speed skill.
                    case Constants.PROPERTY_SKILL_SPEED:
                        Integer newBaseSpeed = (Integer)propertyChangeEvent.getNewValue();
                        updateSpeedBase(newBaseSpeed);
                        updateTotalSpeed(newBaseSpeed + G.activePlayer.getEquipment().getSpeedBoost());
                        break;
                }
            }
        });

        G.activePlayer.getEquipment().addPropertychangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                switch (propertyChangeEvent.getPropertyName()) {
                    case Constants.PROPERTY_EQUIPMENT_REPLACED:
                        Item newItem = (Item) propertyChangeEvent.getNewValue();
                        adapter.update(newItem);

                        // Calculate the new Item's stat boost and update total
                        // stat text views for new item type.
                        Integer newItemBoost = newItem.getStatBoost();
                        switch (newItem.getType()) {
                            case WEAPON:
                                Integer strength = G.activePlayer.getSkills().getStrength();
                                updateTotalStrength(strength + newItemBoost);
                                break;
                            case HELMET:
                                Integer vitality = G.activePlayer.getSkills().getVitality();
                                updateTotalVitality(vitality + newItemBoost);
                                break;
                            case BOOTS:
                                Integer speed = G.activePlayer.getSkills().getSpeed();
                                updateTotalSpeed(speed + newItemBoost);
                                break;
                        }
                }
            }
        });
    }
}
