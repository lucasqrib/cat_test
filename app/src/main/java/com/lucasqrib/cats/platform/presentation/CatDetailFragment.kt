package com.lucasqrib.cats.platform.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.lucasqrib.cats.R
import com.lucasqrib.cats.databinding.FragmentCatDetailBinding
import com.lucasqrib.cats.domain.model.Cat
import com.lucasqrib.cats.domain.model.CatWeight
import com.lucasqrib.cats.platform.di.GlideApp
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class CatDetailFragment : Fragment() {


    private val argument: CatDetailFragmentArgs by navArgs()
    private val viewModel: CatSharedViewModel by activityViewModels()
    lateinit var binding: FragmentCatDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCatDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCatById(argument.catId)?.let {
            initViews(it)
        }

    }


    private fun initViews(cat: Cat) {
        setCatImage(cat.image?.url)
        binding.apply {
            tvCatName.text = cat.name
            tvCatDescription.text = cat.description
            setLifeSpan(cat.lifeSpan)
            setWeight(cat.weight)
            setTraits(cat)
            setTraitLevels(cat)
        }
    }

    private fun setTraits(cat: Cat) {
        binding.chipGroupLayout.apply {
            chipIndoor.isChecked = cat.indoor ?: false
            chipLap.isChecked = cat.lap ?: false
            chipExperimental.isChecked = cat.experimental ?: false
            chipHairless.isChecked = cat.hairless ?: false
            chipNatural.isChecked = cat.natural ?: false
            chipRare.isChecked = cat.rare ?: false
            chipRex.isChecked = cat.rex ?: false
            chipSuppressedTail.isChecked = cat.suppressedTail ?: false
            chipShortLeg.isChecked = cat.shortLegs ?: false
            chipHypoallergenic.isChecked = cat.hypoallergenic ?: false
        }
    }

    private fun setTraitLevels(cat: Cat) {
        binding.traitLevelLayout.apply {
            pbAdaptability.progress = getTraitPercent(cat.adaptability)
            pbAffection.progress = getTraitPercent(cat.affectionLevel)
            pbCatFriendly.progress = getTraitPercent(cat.catFriendly)
            pbChildFriendly.progress = getTraitPercent(cat.childFriendly)
            pbDogFriendly.progress = getTraitPercent(cat.dogFriendly)
            pbEnergy.progress = getTraitPercent(cat.energyLevel)
            pbGrooming.progress = getTraitPercent(cat.grooming)
            pbHealthIssues.progress = getTraitPercent(cat.healthIssues)
            pbIntelligence.progress = getTraitPercent(cat.intelligence)
            pbSalable.progress = getTraitPercent(cat.bidability)
            pbShedding.progress = getTraitPercent(cat.sheddingLevel)
            pbSocialNeeds.progress = getTraitPercent(cat.socialNeeds)
            pbStrangerFriendly.progress = getTraitPercent(cat.strangerFriendly)
            pbVocalisation.progress = getTraitPercent(cat.vocalisation)

        }
    }

    private fun getTraitPercent(level: Int?): Int {
        val progressMaxRatio = 100 / 5
        return level?.let {
            it * progressMaxRatio
        } ?: progressMaxRatio

    }


    private fun setLifeSpan(lifeSpan: String?) {
        lifeSpan?.let {
            binding.tvLifespan.text = getString(R.string.life_span_text, it)
        } ?: run {
            binding.tvLifespanTitle.visibility = View.INVISIBLE
        }
    }

    private fun setWeight(weight: CatWeight?) {

        weight?.let {
            when (Locale.getDefault().country.uppercase()) {
                "US" -> binding.tvWeight.text =
                    getString(R.string.weight_text_imperial, it.imperial)
                else -> binding.tvWeight.text = getString(R.string.weight_text_metric, it.metric)
            }
        } ?: run {
            binding.tvWeightTitle.visibility = View.INVISIBLE
        }
    }

    private fun setCatImage(imgUrl: String?) {
        GlideApp.with(this)
            .load(imgUrl)
            .fallback(R.drawable.cat_placeholder)
            .centerCrop()
            .into(binding.ivCatDetail)

    }

}